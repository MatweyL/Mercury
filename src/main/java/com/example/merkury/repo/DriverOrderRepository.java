package com.example.merkury.repo;

import com.example.merkury.entity.DriverOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverOrderRepository extends JpaRepository<DriverOrder, Long> {

    List<DriverOrder> findAllByDriver_Id(Long driverId);

}
