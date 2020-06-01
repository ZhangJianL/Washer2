package com.zjl.washer.DAO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class DeviceDetail {

    /*设备详情*/
    @Id
    private Integer deviceId;
    private String deviceName;
    private Date producedDate;
    private String status;
    private String description;
    private Integer areaId;
    private Integer platformId;
    private Integer typeId;
    private String message;
    private String brand;
    private String mangerId;
    private String mangerName;
    private String openId;
    private String nickName;

}
