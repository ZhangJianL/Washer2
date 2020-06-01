package com.zjl.washer.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zjl.washer.DAO.Order;
import com.zjl.washer.enums.OrderStatusEnum;
import com.zjl.washer.enums.PayStatusEnum;
import com.zjl.washer.utils.EnumUtil;
import com.zjl.washer.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    /*订单id*/
    private String orderId;

    /*所在区域id*/
    private Integer areaId;

    /*买家名字*/
    private String userName;

    /*买家openid*/
    private String buyerOpenId;

    /*设备id*/
    private Integer deviceId;

    /*金额*/
    private Double amount;

    /*下单状态,默认0为新下单*/
    private Integer orderStatus;

    /*模式*/
    private String mode;

    /*支付状态，默认0为未支付*/
    private Integer payStatus;

    /** 创建时间. */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date creatTime;

    /*更新时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /*开始时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
            private Date startTime;

    List<Order> orderList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);}

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){return EnumUtil.getByCode(payStatus, PayStatusEnum.class);}
}
