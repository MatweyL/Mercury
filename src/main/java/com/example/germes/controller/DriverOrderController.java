package com.example.germes.controller;

import com.example.germes.entity.UserOrder;
import com.example.germes.repo.DriverOrderRepository;
import com.example.germes.repo.DriverRepository;
import com.example.germes.repo.UserOrderRepository;
import com.example.germes.service.DriverOrderService;
import com.example.germes.service.DriverService;
import com.example.germes.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/driver_order")
public class DriverOrderController {

    @Autowired
    private final DriverService driverService;


    @Autowired
    private final DriverOrderService driverOrderService;

    @Autowired
    private final UserOrderService userOrderService;

    public DriverOrderController(DriverService driverService, DriverOrderService driverOrderService, UserOrderService userOrderService) {
        this.driverService = driverService;
        this.driverOrderService = driverOrderService;
        this.userOrderService = userOrderService;
    }

    @GetMapping({"/list", "/"})
    private ModelAndView getAllUserUnclosedOrders() {
        ModelAndView mav = new ModelAndView("admin/driver/unclosed_orders/list-unclosed-users-orders");
        mav.addObject("userOrders", userOrderService.findAllByIsClosedFalse());
        return mav;
    }

    @GetMapping("/showDriverOrderAssignForm")
    private ModelAndView showDriverOrderAssignForm(@RequestParam Long userOrderId) {
        ModelAndView mav = new ModelAndView("admin/driver/unclosed_orders/assign-driver-to-user-order");
        UserOrder userOrder = userOrderService.getById(userOrderId);
        mav.addObject("userOrder", userOrder);
        mav.addObject("freeDrivers", driverService.getAvailableDriversWithCarTypeOnDate(userOrder.getCarType(), userOrder.getDateOfDispatch(), userOrder.getDuration()));
        return mav;
    }

    @GetMapping("/assignDriver")
    private String assignDriver(@RequestParam Long userOrderId, @RequestParam Long driverId) {
        driverOrderService.assignDriverToUserOrder(userOrderId, driverId);
        return "redirect:list";
    }

}
