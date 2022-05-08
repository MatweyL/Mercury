package com.example.germes.repo;

import com.example.germes.entity.DriverOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverOrderRepository extends JpaRepository<DriverOrder, Long> {
}
