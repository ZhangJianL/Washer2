package com.zjl.washer.repository;

import com.zjl.washer.DAO.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundRepository extends JpaRepository<Fund,String> {
    //    List<OrderDetail> findByOrderId(String orderId);
    List<Fund> findByMerchantId(String merchantId);
}
