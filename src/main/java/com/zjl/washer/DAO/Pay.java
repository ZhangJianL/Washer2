package com.zjl.washer.DAO;

import com.zjl.washer.enums.OrderStatusEnum;
import com.zjl.washer.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
public class Pay {
    @Id
    private String orderId;

    private Integer deviceId;

    private String buyerOpenid;

    /*订单状态，默认0为新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /*支付状态，默认0为未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Double amount;

    private String mode;

    private String description;

//    private Timestamp remainTime;
    private String userName;

    private String area;

    private String deviceName;
}
