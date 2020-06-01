package com.zjl.washer.repository;

import com.zjl.washer.DAO.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    List<Order> findByOrderId(String orderId);
    //    List<OrderDetail> findByOrderId(String orderId);
}
