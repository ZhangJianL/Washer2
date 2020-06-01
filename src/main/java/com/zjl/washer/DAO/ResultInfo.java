package com.zjl.washer.DAO;

import lombok.Data;

@Data
public class ResultInfo {
    private Boolean success;
    private String message;

    public ResultInfo(){}

    public ResultInfo(Boolean success,String message){
        this.success = success;
        this.message = message;
    }
}
