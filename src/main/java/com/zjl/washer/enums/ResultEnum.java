package com.zjl.washer.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    ORDER_NOT_EXIST(0,"订单不存在"),
    DEVICE_NOT_EXIST(1,"设备不存在"),
    DEVICE_STATUS_ERROR(2,"设备状态不正确"),
    ORDER_OWENER_ERROR(3,"订单openid不一致"),
    DEVICE_USED(4,"机器使用中"),
    PAY_NOT_EXIST(5,"支付不存在"),
    ORDER_STATUS_ERROR(6,"订单状态不正确"),
    ORDER_CANCEL_FAIL(7,"订单取消失败"),
    PAY_CANCEL_FAIL(8,"修改支付状态失败"),
    ORDER_START_FAIL(9,"订单开始失败"),
    ORDER_FINISH_FAIL(10,"订单完结失败"),
    PAY_STATUS_ERROR(11,"订单支付状态错误"),
    ORDER_PAID_FAIL(12,"订单paid存储失败"),
    PAY_PAID_FAIL(13,"支付paid存储失败"),
    RECORD_CANCEL_FAIL(14,"取消订单记录失败"),
    RECORD_FINISH_FAIL(15,"完结订单记录失败"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
