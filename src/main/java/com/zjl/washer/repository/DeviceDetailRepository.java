package com.zjl.washer.repository;

import com.zjl.washer.DAO.DeviceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceDetailRepository extends JpaRepository<DeviceDetail,Integer> {
    List<DeviceDetail> findByDeviceId(Integer deviceId);
//    List<DeviceDetail> findByDeviceId(Integer deviceId,List list);
}
