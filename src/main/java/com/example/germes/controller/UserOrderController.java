package com.example.germes.controller;

import com.example.germes.entity.Driver;
import com.example.germes.entity.UserOrder;
import com.example.germes.repo.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.MalformedParameterizedTypeException;

@Controller
@RequestMapping(value = "/user_order")
public class UserOrderController {

    @Autowired
    private final UserOrderRepository userOrderRepository;

    public UserOrderController(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }

    @GetMapping({"/list", "/"})
    private ModelAndView getAllUserOrders() {
        ModelAndView mav = new ModelAndView("user/list-user-orders");
        mav.addObject("userOrders", userOrderRepository.findAll());
        return mav;
    }

    @GetMapping("/addUserOrderForm")
    private ModelAndView addUserOrderForm() {
        ModelAndView mav = new ModelAndView("user/add-user-order-form");
        UserOrder newUserOrder = new UserOrder();
        mav.addObject("userOrder", newUserOrder);
        return mav;
    }

    @PostMapping("/saveUserOrder")
    private String saveUserOrder(@ModelAttribute UserOrder userOrder) {
        System.out.println(userOrder.toStringInConsole());
        userOrderRepository.save(userOrder);
        return "redirect:list";
    }

}
