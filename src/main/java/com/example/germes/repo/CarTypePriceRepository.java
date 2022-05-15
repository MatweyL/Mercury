package com.example.germes.repo;

import com.example.germes.entity.CarTypePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypePriceRepository extends JpaRepository<CarTypePrice, Long> {

    CarTypePrice findByCarType(String carType);

}
