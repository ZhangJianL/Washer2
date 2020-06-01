package com.zjl.washer.DAO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
* 地区
* */
@Entity
@Data
public class Area {

    /*地区id*/
    @Id
    @GeneratedValue
    private Integer areaId;
    /*地区名*/
    private String areaName;
    /*地址*/
    private String location;
    /*管理者id*/
    private String managerId;

    public Area(){

    }
    public Area(String areaName,String location,String managerId){
        this.areaName = areaName;
        this.location = location;
        this.managerId = managerId;
    }
}
