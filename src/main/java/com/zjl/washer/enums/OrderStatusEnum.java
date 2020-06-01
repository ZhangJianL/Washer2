package com.zjl.washer.enums;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Getter;

@Getter
public enum  OrderStatusEnum implements CodeEnums{
    NEW(0,"新订单"),
    WAIT(1,"已支付，等待开始"),
    WORK(2,"工作中"),
    FINISHED(3,"完结"),
    CANCEL(4,"已取消"),
    ;
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }


}
