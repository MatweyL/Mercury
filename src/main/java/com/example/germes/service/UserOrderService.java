package com.example.germes.service;

import com.example.germes.entity.User;
import com.example.germes.entity.UserOrder;
import com.example.germes.repo.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class UserOrderService {

    @Autowired
    private final UserOrderRepository userOrderRepository;


    public UserOrderService(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    public List<UserOrder> findAll() {
        return userOrderRepository.findAll();
    }

    public List<UserOrder> findAllByIsClosedFalse() {
        return userOrderRepository.findAllByIsClosedFalse();
    }

    public UserOrder getById(Long userOrderId) {
        return userOrderRepository.getById(userOrderId);
    }

    public UserOrder save(UserOrder userOrder) {
        return userOrderRepository.save(userOrder);
    }

    public void setIsClosedUserOrder(UserOrder userOrder, boolean isClosed) {
        userOrder.setIsClosed(isClosed);
    }

    public void setUserOrderDateOfDispatch(UserOrder userOrder, String dateTimeString) {
        LocalDateTime dateOfDispatchLDT = LocalDateTime.parse(dateTimeString);
        Date dateOfDispatch = Date.from(dateOfDispatchLDT.atZone(ZoneId.systemDefault()).toInstant());
        userOrder.setDateOfDispatch(dateOfDispatch);
    }

    public void setUserOrderDeliveryCost(UserOrder userOrder) {
        int PAY_PER_KM = 20;
        userOrder.setDeliveryCost(PAY_PER_KM * userOrder.getDistance());
    }

}
