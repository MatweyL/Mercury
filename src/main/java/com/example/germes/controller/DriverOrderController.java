package com.example.germes.controller;

import com.example.germes.repo.DriverOrderRepository;
import com.example.germes.repo.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/driver_order")
public class DriverOrderController {

    @Autowired
    private final DriverOrderRepository driverOrderRepository;

    @Autowired
    private final UserOrderRepository userOrderRepository;

    public DriverOrderController(DriverOrderRepository driverOrderRepository, UserOrderRepository userOrderRepository) {
        this.driverOrderRepository = driverOrderRepository;
        this.userOrderRepository = userOrderRepository;
    }

    @GetMapping({"/list", "/"})
    private ModelAndView getAllUserUnclosedOrders() {
        ModelAndView mav = new ModelAndView("admin/driver/unclosed_orders/list-unclosed-users-orders");
        mav.addObject("userOrders", userOrderRepository.findAllByIsClosedFalse());
        return mav;
    }

    @GetMapping("showDriverOrderAssignForm")
    private ModelAndView showDriverOrderAssignForm(@RequestParam Long userOrderId) {
        ModelAndView mav = new ModelAndView("admin/driver/unclosed_orders/assign-driver-to-user-order");
        mav.addObject("userOrder", userOrderRepository.getById(userOrderId));
        return mav;
    }
}
