package com.zjl.washer.service.impl;

import com.zjl.washer.DAO.Mode;
import com.zjl.washer.repository.ModeRepository;
import com.zjl.washer.service.ModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeServiceImpl implements ModeService {

    @Autowired
    private ModeRepository repository;

    @Override
    public Mode findOne(String mode) {
        return repository.findOne(mode);
    }
}
