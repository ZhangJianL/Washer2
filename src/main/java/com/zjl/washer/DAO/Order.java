package com.zjl.washer.DAO;

import com.zjl.washer.enums.OrderStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class Order {
    /*订单*/
    @Id
    private String orderId;

    private Integer deviceId;

    private String mode;

    private Date creatTime;

    private String description;

    private String area;

    private Integer areaId;

    private String deviceName;

    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    private Date payTime;

    private Date startTime;

    private Double amount;
}
