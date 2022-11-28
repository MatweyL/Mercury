package com.example.merkury.repo;

import com.example.merkury.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    List<UserOrder> findAllByIsClosedFalse();
    List<UserOrder> findAllByIsClosedTrue();
    List<UserOrder> findAllByUser_id(Long id);
}
