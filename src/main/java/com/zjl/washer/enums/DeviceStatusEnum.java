package com.zjl.washer.enums;

import lombok.Getter;

@Getter
public enum DeviceStatusEnum implements CodeEnums{
    UP(0,"在线"),
    DOWN(1,"下线"),
    WAIT(2,"等待中"),
    WORK(3,"工作中"),
    ;
    private Integer code;

    private String message;

    DeviceStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
