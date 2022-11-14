package com.example.germes.service;

import com.example.germes.entity.DriverData;
import com.example.germes.entity.DriverOrder;
import com.example.germes.entity.User;
import com.example.germes.entity.dto.DriverDataDto;
import com.example.germes.repo.DriverDataRepository;
import com.example.germes.repo.DriverOrderRepository;
import com.example.germes.repo.DriverRepository;
import com.example.germes.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverDataService {


    @Autowired
    private final DriverDataRepository driverDataRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final DriverOrderRepository driverOrderRepository;

    @Autowired
    private final DriverRepository driverRepository;

    public DriverDataService(DriverDataRepository driverDataRepository, UserRepository userRepository, DriverOrderRepository driverOrderRepository, DriverRepository driverRepository) {
        this.driverDataRepository = driverDataRepository;
        this.userRepository = userRepository;
        this.driverOrderRepository = driverOrderRepository;
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
            driverData = driverDataRepository.getDriverDataByDriver_NameAndDriver_Surname(driverDataDto.getDriver().getName(), driverDataDto.getDriver().getSurname());
        }
        if (driverData == null) {
            driverData = new DriverData();
        }
        driverData.setUser(getCurrentUser());
        driverData.setDriver(driverDataDto.getDriver());
        if (driverDataDto.getIs_active() == null) {
            driverData.setIs_active(true);
        } else {
            driverData.setIs_active(driverDataDto.getIs_active());
        }
        if (driverDataDto.getIs_verified() == null) {
            driverData.setIs_verified(false);
        } else {
            driverData.setIs_verified(driverDataDto.getIs_verified());
        }
        driverRepository.save(driverDataDto.getDriver());
        driverDataRepository.save(driverData);
    }

    public void setDriverIsActive(Long driverId, Boolean isActive) {
        DriverData driverData = driverDataRepository.getDriverDataByDriver_Id(driverId);
        driverData.setIs_active(isActive);
    }

    public void setDriverIsVerified(Long driverId, Boolean isVerified) {
        DriverData driverData = driverDataRepository.getDriverDataByDriver_Id(driverId);
        driverData.setIs_verified(isVerified);
    }

    public List<DriverOrder> getCurrentDriverOrders() {
        Long currentUserId = getCurrentUser().getId();
        DriverData driverData = driverDataRepository.getDriverDataByUser_Id(currentUserId);
        if (driverData != null) {
            return driverOrderRepository.findAllByDriver_Id(driverData.getDriver().getId());
        }
        return null;
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
