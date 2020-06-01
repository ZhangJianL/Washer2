package com.zjl.washer.DAO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
public class Refund {
    @Id
    private Integer refundId;
    private Integer deviceId;
    private BigDecimal amount;
    private Timestamp refundTime;
    private Integer uerId;
    private String userName;
    private Integer mode;
    private String area;
    private String deviceName;
}
