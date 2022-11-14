package com.example.germes.repo;

import com.example.germes.entity.Driver;
import com.example.germes.entity.DriverData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverDataRepository extends JpaRepository<DriverData, Long> {

    DriverData getDriverDataByDriver_Id(Long driverId);

    DriverData getDriverDataByUser_Id(Long userId);

    DriverData getDriverDataByDriver_NameAndDriver_Surname(String driverName, String driverSurname);

}
