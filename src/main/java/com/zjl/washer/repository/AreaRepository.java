package com.zjl.washer.repository;

import com.zjl.washer.DAO.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AreaRepository extends JpaRepository<Area,Integer> {
    List<Area> findByAreaIdIn(List<Integer> areaIdList);
    List<Area> findByAreaId(Integer areaId);
}
