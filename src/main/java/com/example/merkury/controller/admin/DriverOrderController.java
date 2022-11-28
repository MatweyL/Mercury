package com.example.merkury.controller.admin;

import com.example.merkury.entity.UserOrder;
import com.example.merkury.service.DriverOrderService;
import com.example.merkury.service.DriverService;
import com.example.merkury.service.UserOrderService;
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

    @GetMapping({"/unclosedUsersOrders","/list", "/"})
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

    @GetMapping("/closedUsersOrders")
    private ModelAndView getAllClosedOrders() {
        ModelAndView mav = new ModelAndView("admin/driver/closed_orders/list-closed-users-orders");
        mav.addObject("userOrders",  userOrderService.findAllByIsClosedTrue());
        return mav;
    }

    @GetMapping("/showUserOrdersDetails")
    private ModelAndView showUserOrdersDetails(@RequestParam Long userOrderId) {
        ModelAndView mav = new ModelAndView("admin/driver/closed_orders/closed-user-order-details");
        mav.addObject("userOrder", userOrderService.getById(userOrderId));
        return mav;
    }

}
