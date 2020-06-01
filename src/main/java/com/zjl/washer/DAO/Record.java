package com.zjl.washer.DAO;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class Record {
    @Id
    @GeneratedValue
    private Integer id;
    private String buyerOpenid;
    private String orderId;
    private Integer deviceId;
    private Date creatTime;
    private Date finishTime;
    private String mode;
    private String description;
    private BigDecimal amount;
//    支付状态
    private Integer payStatus;
//    订单状态
    private String orderStatus;
    private Date payTime;
    private Date startTime;
    private Integer paramId;
    private String area;
    private String deviceName;
    private Timestamp aliveTime;
    private Integer managerId;
    private String location;
}
