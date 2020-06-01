package com.zjl.washer.repository;

import com.zjl.washer.DAO.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Integer> {
    List<Record> findById(Integer id);
    //    List<OrderDetail> findByOrderId(String orderId);
}
