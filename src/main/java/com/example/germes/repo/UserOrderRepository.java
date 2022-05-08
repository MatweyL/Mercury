package com.example.germes.repo;

import com.example.germes.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
}
