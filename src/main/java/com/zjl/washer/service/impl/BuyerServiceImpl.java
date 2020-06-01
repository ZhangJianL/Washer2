package com.zjl.washer.service.impl;

import com.zjl.washer.DTO.OrderDTO;
import com.zjl.washer.enums.ResultEnum;
import com.zjl.washer.exceptions.SellException;
import com.zjl.washer.service.BuyerService;
import com.zjl.washer.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openId,String orderId){
        return checkOrderOwner(openId, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId,String orderId){
        OrderDTO orderDTO = checkOrderOwner(openId, orderId);
        if (orderDTO == null){
            log.error("【取消订单】查不到该订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openId,String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
//        判断是否是自己的
        if (!orderDTO.getBuyerOpenId().equalsIgnoreCase(openId)){
            log.error("【查询订单】订单的openid并不一致，openid={}，orderDTO={}",openId,orderDTO);
            throw new SellException((ResultEnum.ORDER_OWENER_ERROR));
        }
        return orderDTO;
    }
}
