package com.example.merkury.service;

import com.example.merkury.entity.Driver;
import com.example.merkury.entity.DriverOrder;
import com.example.merkury.entity.UserOrder;
import com.example.merkury.entity.UserOrderStatus;
import com.example.merkury.repo.DriverOrderRepository;
import com.example.merkury.repo.DriverRepository;
import com.example.merkury.repo.UserOrderRepository;
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
        userOrder.setStatus(UserOrderStatus.ASSIGNED.getDisplayValue());
        userOrderRepository.save(userOrder);
        driverOrderRepository.save(driverOrder);
    }

    public List<DriverOrder> getDriverOrders(Long driverId) {
        return driverOrderRepository.findAllByDriver_Id(driverId);
    }

}
