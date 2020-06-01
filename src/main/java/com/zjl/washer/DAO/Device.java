package com.zjl.washer.DAO;

import com.zjl.washer.enums.DeviceStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class Device {
    /*
    * 设备
    * */
    @Id
    /*设备id*/
    private Integer deviceId;

    /*设备状态,0在线1下线*/
    private Integer deviceStatus = DeviceStatusEnum.UP.getCode();

    /*所属地区id*/
    private Integer areaId;

    /*生产日期*/
    private Date producedDate;

    /*类型*/
    private Integer typeId;

    /*描述*/
    private String description;

    /*设备名称*/
    private String deviceName;

    /*平台id*/
    private Integer platformId;

    /*管理者id*/
    private Integer managerId;
}
