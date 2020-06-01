package com.zjl.washer.service.impl;

import com.zjl.washer.DAO.Area;
import com.zjl.washer.repository.AreaRepository;
import com.zjl.washer.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository repository;

    @Override
    public Area findOne(Integer areaId) {
        return repository.findOne(areaId);
    }
}
