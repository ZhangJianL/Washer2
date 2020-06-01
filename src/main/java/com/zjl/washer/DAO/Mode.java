package com.zjl.washer.DAO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
public class Mode {
    @Id
    private String mode;
    private String modeName;
    private Double amount;
    private Integer times;
    private String waterLevel;
    private String aliveTime;
    private String frequency;
    private String dryTime;
    private String description;
}
