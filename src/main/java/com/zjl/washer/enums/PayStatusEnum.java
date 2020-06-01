package com.zjl.washer.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnums{

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    CANCEL(2,"订单取消"),

    ;
    private Integer code;

    private String message;

    PayStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
