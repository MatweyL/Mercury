package com.example.germes.service;

import com.example.germes.entity.Driver;
import com.example.germes.entity.DriverOrder;
import com.example.germes.entity.UserOrder;
import com.example.germes.repo.DriverOrderRepository;
import com.example.germes.repo.DriverRepository;
import com.example.germes.repo.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverOrderService {

    @Autowired
    private final DriverRepository driverRepository;

    @Autowired
    private final DriverOrderRepository driverOrderRepository;

    @Autowired
    private final UserOrderRepository userOrderRepository;


    public DriverOrderService(DriverRepository driverRepository, DriverOrderRepository driverOrderRepository, UserOrderRepository userOrderRepository) {
        this.driverRepository = driverRepository;
        this.driverOrderRepository = driverOrderRepository;
        this.userOrderRepository = userOrderRepository;
    }

    public void assignDriverToUserOrder(Long userOrderId, Long driverId) {
        UserOrder userOrder = userOrderRepository.getById(userOrderId);
        userOrder.setIsClosed(true);
        Driver driver = driverRepository.getById(driverId);
        DriverOrder driverOrder = new DriverOrder();
        driverOrder.setUserOrder(userOrder);
        driverOrder.setDriver(driver);
        userOrderRepository.save(userOrder);
        driverOrderRepository.save(driverOrder);
    }
}
