package com.example.merkury.repo;

import com.example.merkury.entity.CarTypePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypePriceRepository extends JpaRepository<CarTypePrice, Long> {

    CarTypePrice findByCarType(String carType);

}
