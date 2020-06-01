package com.zjl.washer.repository;

import com.zjl.washer.DAO.Pay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayRepository extends JpaRepository<Pay,String> {
    List<Pay> findByOrderId(String orderId);

    Page<Pay> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
    //    List<OrderDetail> findByOrderId(String orderId);
}
