package com.zjl.washer.repository;

import com.zjl.washer.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    //    List<OrderDetail> findByOrderId(String orderId);
    List<User> findById(Integer id);
}
