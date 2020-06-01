package com.zjl.washer.service;

import com.zjl.washer.DAO.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeviceService {

    Device findOne(Integer deviceId);

    Page<Device> findAll(Pageable pageable);

    Device save(Device device);

//    上线机器
    Device onSystem(Integer deviceId);

//    机器下线
    Device offSystem(Integer deviceId);

//    机器使用,且等待使用
    Device waitWorking(Integer deviceId);

//    机器使用，开始工作
    Device startWorking(Integer deviceId);

    /*查询所有机器*/
    List<Device> findUpAll();
}
