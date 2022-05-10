package com.example.germes.repo;

import com.example.germes.entity.Car;
import com.example.germes.entity.CarType;
import com.example.germes.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findAllByCar_carType(String carType);

}
