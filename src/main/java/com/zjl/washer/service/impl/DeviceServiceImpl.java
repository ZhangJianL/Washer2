package com.zjl.washer.service.impl;

import com.zjl.washer.DAO.Device;
import com.zjl.washer.enums.DeviceStatusEnum;
import com.zjl.washer.enums.ResultEnum;
import com.zjl.washer.exceptions.SellException;
import com.zjl.washer.repository.DeviceRepository;
import com.zjl.washer.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository repository;

    @Override
    public Device findOne(Integer deviceId){
        return repository.findOne(deviceId);
    }

    @Override
    public List<Device> findUpAll(){
        return repository.findByDeviceStatus(DeviceStatusEnum.UP.getCode());
    }

    @Override
    public Page<Device> findAll(Pageable pageable){ return repository.findAll(pageable);}

    @Override
    public Device save(Device device) { return repository.save(device);}


    @Override
    public Device onSystem(Integer deviceId){
        Device device = repository.findOne(deviceId);
        if (device == null){
            throw new SellException(ResultEnum.DEVICE_NOT_EXIST);
        }
        if (device.getDeviceStatus() == DeviceStatusEnum.UP.getCode() ){
            throw new SellException(ResultEnum.DEVICE_STATUS_ERROR);
        }
//        更新上线状态
        device.setDeviceStatus(DeviceStatusEnum.UP.getCode());
        return repository.save(device);
    }

    @Override
    public Device offSystem(Integer deviceId){
        Device device = repository.findOne(deviceId);
        if (device == null){
            throw new SellException(ResultEnum.DEVICE_NOT_EXIST);
        }
        if (device.getDeviceStatus() == DeviceStatusEnum.DOWN.getCode()){
            throw new SellException(ResultEnum.DEVICE_STATUS_ERROR);
        }
//        更新下线状态
        device.setDeviceStatus(DeviceStatusEnum.DOWN.getCode());
        return repository.save(device);
    }

    @Override
    public Device waitWorking(Integer deviceId) {
        Device device = repository.findOne(deviceId);
        if (device == null){
            throw new SellException(ResultEnum.DEVICE_NOT_EXIST);
        }
        if (device.getDeviceStatus() == DeviceStatusEnum.WAIT.getCode() || device.getDeviceStatus() == DeviceStatusEnum.WORK.getCode()){
            throw new SellException(ResultEnum.DEVICE_USED);
        }
//        机器等待使用
        device.setDeviceStatus(DeviceStatusEnum.WAIT.getCode());
        return repository.save(device);
    }

    @Override
    public Device startWorking(Integer deviceId) {
        Device device = repository.findOne(deviceId);
        if (device == null){
            throw new SellException(ResultEnum.DEVICE_NOT_EXIST);
        }
        if (device.getDeviceStatus() == DeviceStatusEnum.WAIT.getCode() || device.getDeviceStatus() == DeviceStatusEnum.WORK.getCode()){
            throw new SellException(ResultEnum.DEVICE_USED);
        }
//        机器开始使用
        device.setDeviceStatus(DeviceStatusEnum.WORK.getCode());
        return repository.save(device);
    }
}
