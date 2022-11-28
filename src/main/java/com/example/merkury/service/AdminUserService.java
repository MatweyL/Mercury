package com.example.merkury.service;

import com.example.merkury.entity.Role;
import com.example.merkury.entity.User;
import com.example.merkury.entity.UserOrder;
import com.example.merkury.repo.UserOrderRepository;
import com.example.merkury.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserOrderRepository userOrderRepository;

    public AdminUserService(UserRepository userRepository, UserOrderRepository userOrderRepository) {
        this.userRepository = userRepository;
        this.userOrderRepository = userOrderRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAllByRole(Role.USER);
    }

    public User getUserById(Long userId) {
        return userRepository.getById(userId);
    }

    public List<UserOrder> getUserUserOrders(Long userId) {
        return userOrderRepository.findAllByUser_id(userId);
    }

    public UserOrder getUserOrder(Long userOrderId) {
        return userOrderRepository.getById(userOrderId);
    }

}
