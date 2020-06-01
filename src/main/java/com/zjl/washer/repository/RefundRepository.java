package com.zjl.washer.repository;

import com.zjl.washer.DAO.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefundRepository extends JpaRepository<Refund,Integer> {
    //    List<OrderDetail> findByOrderId(String orderId);
    List<Refund> findByRefundId(Integer refundId);
}
