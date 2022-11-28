package com.example.merkury.service;

import com.example.merkury.entity.Driver;
import com.example.merkury.entity.DriverOrder;
import com.example.merkury.entity.UserOrder;
import com.example.merkury.entity.UserOrderStatus;
import com.example.merkury.repo.DriverOrderRepository;
import com.example.merkury.repo.DriverRepository;
import com.example.merkury.repo.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatusService {

    @Autowired
    private final DriverOrderRepository driverOrderRepository;

    @Autowired
    private final UserOrderRepository userOrderRepository;

    @Autowired
    private final DriverRepository driverRepository;

    public StatusService(DriverOrderRepository driverOrderRepository, UserOrderRepository userOrderRepository, DriverRepository driverRepository) {
        this.driverOrderRepository = driverOrderRepository;
        this.userOrderRepository = userOrderRepository;
        this.driverRepository = driverRepository;
    }

    @Scheduled(fixedDelay = 60000)
    public void changeAssignedUserOrderStatus() {
        List<DriverOrder> driversOrders = driverOrderRepository.findAll();
        for (DriverOrder driverOrder: driversOrders) {
            UserOrder userOrder = driverOrder.getUserOrder();
            double duration = userOrder.getDuration();
            Date dateOfDispatch = userOrder.getDateOfDispatch();
            Date dateOfArrival = Date.from(dateOfDispatch.toInstant().plusSeconds(Math.round(duration * 60)));
            Date dateCurrent = new Date();
            if (dateCurrent.after(dateOfDispatch) &&
                    dateCurrent.before(dateOfArrival) &&
                    !userOrder.getStatus().equals(UserOrderStatus.TRANSPORTED.getDisplayValue())) {
                Driver driver = driverOrder.getDriver();
                driver.setIsBusy(true);
                driverRepository.save(driver);
                userOrder.setStatus(UserOrderStatus.TRANSPORTED.getDisplayValue());
                userOrderRepository.save(userOrder);
            }
            if (dateCurrent.after(dateOfArrival) &&
                    !userOrder.getStatus().equals(UserOrderStatus.DELIVERED.getDisplayValue())) {
                Driver driver = driverOrder.getDriver();
                driver.setIsBusy(false);
                driverRepository.save(driver);
                userOrder.setStatus(UserOrderStatus.DELIVERED.getDisplayValue());
                userOrderRepository.save(userOrder);
            }
        }
    }

}
