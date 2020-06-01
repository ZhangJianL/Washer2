package com.zjl.washer.service.impl;

import com.zjl.washer.Converter.Pay2OrderDTOConverter;
import com.zjl.washer.DAO.*;
import com.zjl.washer.DTO.OrderDTO;
import com.zjl.washer.enums.OrderStatusEnum;
import com.zjl.washer.enums.PayStatusEnum;
import com.zjl.washer.enums.ResultEnum;
import com.zjl.washer.exceptions.SellException;
import com.zjl.washer.repository.*;
import com.zjl.washer.service.DeviceService;
import com.zjl.washer.service.ModeService;
import com.zjl.washer.service.OrderService;
import com.zjl.washer.service.PayService;
import com.zjl.washer.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private PayService payService;

    @Autowired
    private ModeService modeService;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

//        1.查询设备
        Device device = deviceService.findOne(orderDTO.getDeviceId());
        if (device == null){
            log.info("【下单】该设备不存在数据库中，deviceId={}",device.getDeviceId());
            throw new SellException(ResultEnum.DEVICE_NOT_EXIST);
        }

//      2.查询价格
        Mode mode = modeService.findOne(orderDTO.getMode());
        Double amount = mode.getAmount();
        orderDTO.setAmount(amount);
//        3.订单入库
        Order order = new Order();
        orderDTO.setOrderId(orderId);
        /*order.setOrderId(orderId);
        order.setDeviceId(device.getDeviceId());
        order.setMode(mode.getMode());
        order.setDescription(mode.getDescription());*/
        BeanUtils.copyProperties(orderDTO,order);
        BeanUtils.copyProperties(device,order);
        BeanUtils.copyProperties(mode,order);

        Area area = areaRepository.findOne(device.getAreaId());
        /*order.setArea(area.getAreaName());
        order.setDeviceName(device.getDeviceName());*/
        BeanUtils.copyProperties(area,order);
        orderRepository.save(order);

//        4.支付状态入库
        Pay pay = new Pay();
        BeanUtils.copyProperties(orderDTO,pay);
        BeanUtils.copyProperties(order,pay);
        payRepository.save(pay);

//        5.机器状态修改
        deviceService.waitWorking(device.getDeviceId());

//        TODO: 消息推送以后补，下单了以后发送消息
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        Order order = orderRepository.findOne(orderId);
        if (order == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        Pay pay = payRepository.findOne(orderId);
        if (pay == null){
            throw new SellException(ResultEnum.PAY_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(pay,orderDTO);
        BeanUtils.copyProperties(order,orderDTO);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<Pay> payPage = payRepository.findByBuyerOpenid(buyerOpenId,pageable);

        List<OrderDTO> orderDTOList = Pay2OrderDTOConverter.convert(payPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable, payPage.getTotalElements());
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        Pay pay = payRepository.findOne(orderDTO.getOrderId());
        Order order = orderRepository.findOne(orderDTO.getOrderId());

//        判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()) && !orderDTO.getOrderStatus().equals(OrderStatusEnum.WAIT.getCode())){
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

//        修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,order);
        Order updateResult = orderRepository.save(order);
        if (updateResult == null){
            log.error("【取消订单】修改订单状态失败，order={}",order);
            throw new SellException(ResultEnum.ORDER_CANCEL_FAIL);
        }

//        如果已支付，则退款,修改支付状态
        if (orderDTO.getOrderStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            payService.refund(orderDTO);
        }
        pay.setPayStatus(PayStatusEnum.CANCEL.getCode());
        Pay updateResult2 = payRepository.save(pay);
        if (updateResult2 == null){
            log.error("【订单取消】修改支付状态失败，pay={}",pay);
            throw new SellException(ResultEnum.PAY_CANCEL_FAIL);
        }

//        机器恢复上线状态
        deviceService.onSystem(orderDTO.getDeviceId());

//        记录到record中
        Record record = new Record();
        Area area = areaRepository.findOne(orderDTO.getDeviceId());
        BeanUtils.copyProperties(order,record);
        BeanUtils.copyProperties(pay,record);
        BeanUtils.copyProperties(area,record);
        Record updateResult3 = recordRepository.save(record);
        if (updateResult3 == null){
            log.error("【取消订单】存储记录失败，record={}",record);
            throw new SellException(ResultEnum.RECORD_CANCEL_FAIL);
        }

//        TODO:推送之后写
        return orderDTO;
    }

    @Override
    public OrderDTO start(OrderDTO orderDTO) {
        Device device = deviceRepository.findOne(orderDTO.getDeviceId());
        Order order = orderRepository.findOne(orderDTO.getOrderId());
//        修改订单记录和机器状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.WAIT)){
            log.error("【开始订单】订单状态错误，order={}",order);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDTO.setOrderStatus(OrderStatusEnum.WORK.getCode());
        order.setOrderStatus(OrderStatusEnum.WORK.getCode());
        Order updateResult = orderRepository.save(order);
        if (updateResult == null){
            log.error("【开始订单】更新失败,order={}",order);
            throw new SellException(ResultEnum.ORDER_START_FAIL);
        }
//        修改机器状态，开始工作
        deviceService.startWorking(device.getDeviceId());
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        Order order = orderRepository.findOne(orderDTO.getOrderId());
//        判断订单状态，并且改为完结
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.WORK)){
            log.error("【完结订单】订单状态错误，order={}",order);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO,order);
        Order updateResult = orderRepository.save(order);
        if (updateResult == null){
            log.error("【完结订单】更新失败，order={}",order);
            throw new SellException(ResultEnum.ORDER_FINISH_FAIL);
        }
//        修改机器状态，重新上线
        deviceService.onSystem(order.getDeviceId());
//        上传记录
        Record record = new Record();
        BeanUtils.copyProperties(areaRepository.findByAreaId(order.getAreaId()),record);
        BeanUtils.copyProperties(payRepository.findByOrderId(order.getOrderId()),record);
        BeanUtils.copyProperties(order,record);
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        Order order = orderRepository.findOne(orderDTO.getOrderId());
        Pay pay = payRepository.findOne(orderDTO.getOrderId());
//        验证订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW)){
            log.error("【支付完成】订单状态错误，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
//        验证支付状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.WAIT)){
            log.error("【支付完成】支付状态错误,orderId={},payStatus={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.PAY_STATUS_ERROR);
        }
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderDTO.setOrderStatus(OrderStatusEnum.WAIT.getCode());
        BeanUtils.copyProperties(orderDTO,order);
        BeanUtils.copyProperties(orderDTO,pay);
        Order result = orderRepository.save(order);
        if (result == null){
            log.error("【支付完成】更新失败，order={}",order);
            throw new SellException(ResultEnum.ORDER_PAID_FAIL);
        }
        Pay result2 = payRepository.save(pay);
        if (result2 == null){
            log.error("【支付完成】更新失败，pay={}",pay);
            throw new SellException(ResultEnum.PAY_PAID_FAIL);
        }
        return orderDTO;
    }
}
