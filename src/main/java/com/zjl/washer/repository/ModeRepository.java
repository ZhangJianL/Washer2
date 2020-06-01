package com.zjl.washer.repository;

import com.zjl.washer.DAO.Mode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModeRepository extends JpaRepository<Mode,String> {
    List<Mode> findByMode(String mode);
    //    List<OrderDetail> findByOrderId(String orderId);
}
