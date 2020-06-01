package com.zjl.washer.repository;

import com.zjl.washer.DAO.ManagerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerInfoRepository extends JpaRepository<ManagerInfo,String> {
    List<ManagerInfo> findByManagerId(String managerId);
}
