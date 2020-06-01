package com.zjl.washer.repository;

import com.zjl.washer.DAO.Device;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,String> {
    List<Device> findByDeviceStatus(Integer deviceStatus);

    Device findOne(Integer deviceId);
    //    List<OrderDetail> findByOrderId(String orderId);
//    List<Device> findByDeviceId(Integer deviceId,List list);
}
