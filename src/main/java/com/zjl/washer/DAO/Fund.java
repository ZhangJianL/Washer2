package com.zjl.washer.DAO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Data
@Entity
public class Fund {
    /*收入资金*/
    @Id
    /*商家*/
    private String merchantId;
    private String merchant;
    private BigDecimal amount;
    private DecimalFormat ratio;
    private Integer mchId;
    private Double collectMoney;
}
