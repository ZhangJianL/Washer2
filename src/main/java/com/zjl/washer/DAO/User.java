package com.zjl.washer.DAO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {
    /*用户表*/
    @Id
    @GeneratedValue
    private Integer id;

    private String openId;

    private String userName;

    private String headImgUrl;
}
