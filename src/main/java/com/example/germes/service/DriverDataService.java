package com.example.germes.service;

import com.example.germes.entity.DriverData;
import com.example.germes.entity.User;
import com.example.germes.entity.UserData;
import com.example.germes.entity.dto.DriverDataDto;
import com.example.germes.entity.dto.UserDataDto;
import com.example.germes.repo.DriverDataRepository;
import com.example.germes.repo.DriverRepository;
import com.example.germes.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DriverDataService {


    @Autowired
    private final DriverDataRepository driverDataRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DriverRepository driverRepository;

    public DriverDataService(DriverDataRepository driverDataRepository, UserRepository userRepository, DriverRepository driverRepository) {
        this.driverDataRepository = driverDataRepository;
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
    }

    public DriverDataDto getDriverDataDto() {
        DriverData driverData = driverDataRepository.getDriverDataByUser_Id(getCurrentUser().getId());
        DriverDataDto driverDataDto = DriverDataDto.fromDriverData(driverData);
        if (driverDataDto.getUser() == null) {
            driverDataDto.setUser(getCurrentUser());
        }
        return driverDataDto;
    }

    public DriverDataDto getDriverDataDtoByDriverId(Long driverId) {
        DriverData driverData = driverDataRepository.getDriverDataByDriver_Id(driverId);
        return DriverDataDto.fromDriverData(driverData);
    }

    public DriverDataDto getDriverDataDtoByUserId(Long userId) {
        DriverData driverData = driverDataRepository.getDriverDataByDriver_Id(userId);
        return DriverDataDto.fromDriverData(driverData);
    }

    public void update(DriverDataDto driverDataDto) {
        DriverData driverData = driverDataRepository.getDriverDataByUser_Id(getCurrentUser().getId());
        if (driverData == null) {
            driverData = new DriverData();
        }
        driverData.setUser(getCurrentUser());
        driverData.setDriver(driverDataDto.getDriver());
        driverData.setIs_active(driverDataDto.getIs_active());
        driverData.setIs_verified(driverDataDto.getIs_verified());
        driverRepository.save(driverDataDto.getDriver());
        driverDataRepository.save(driverData);
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
