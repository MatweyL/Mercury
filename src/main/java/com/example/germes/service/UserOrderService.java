package com.example.germes.service;

import com.example.germes.entity.User;
import com.example.germes.entity.UserOrder;
import com.example.germes.entity.UserOrderStatus;
import com.example.germes.repo.CarTypePriceRepository;
import com.example.germes.repo.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class UserOrderService {

    @Autowired
    private final UserOrderRepository userOrderRepository;

    @Autowired
    private final CarTypePriceRepository carTypePriceRepository;

    public UserOrderService(UserOrderRepository userOrderRepository, CarTypePriceRepository carTypePriceRepository) {
        this.userOrderRepository = userOrderRepository;
        this.carTypePriceRepository = carTypePriceRepository;
    }

    public List<UserOrder> findAll() {
        return userOrderRepository.findAll();
    }

    public List<UserOrder> findAllCurrentUserOrders() { return userOrderRepository.findAllByUser_id(getCurrentUser().getId());}

    public List<UserOrder> findAllByIsClosedFalse() {
        return userOrderRepository.findAllByIsClosedFalse();
    }

    public UserOrder getById(Long userOrderId) {
        return userOrderRepository.getById(userOrderId);
    }

    public UserOrder save(UserOrder userOrder) {
        userOrder.setUser(getCurrentUser());
        userOrder.setStatus(UserOrderStatus.NOT_ASSIGNED.getDisplayValue());
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
        double costPerKm = carTypePriceRepository.findByCarType(userOrder.getCarType()).getCostPerKm();
        userOrder.setDeliveryCost(costPerKm * userOrder.getDistance());
    }

    public User getCurrentUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(currentUser.getId() + " " + currentUser.getUsername());
        return currentUser;
    }

    public List<UserOrder> findAllByIsClosedTrue() {
        return userOrderRepository.findAllByIsClosedTrue();
    }
}
