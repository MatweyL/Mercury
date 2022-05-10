package com.example.germes.controller;

import com.example.germes.entity.Driver;
import com.example.germes.entity.DriverOrder;
import com.example.germes.entity.UserOrder;
import com.example.germes.repo.DriverOrderRepository;
import com.example.germes.repo.DriverRepository;
import com.example.germes.repo.UserOrderRepository;
import com.example.germes.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/driver_order")
public class DriverOrderController {

    @Autowired
    private final DriverService driverService;

    @Autowired
    private final DriverRepository driverRepository;

    @Autowired
    private final DriverOrderRepository driverOrderRepository;

    @Autowired
    private final UserOrderRepository userOrderRepository;

    public DriverOrderController(DriverService driverService, DriverRepository driverRepository, DriverOrderRepository driverOrderRepository, UserOrderRepository userOrderRepository) {
        this.driverService = driverService;
        this.driverRepository = driverRepository;
        this.driverOrderRepository = driverOrderRepository;
        this.userOrderRepository = userOrderRepository;
    }

    @GetMapping({"/list", "/"})
    private ModelAndView getAllUserUnclosedOrders() {
        ModelAndView mav = new ModelAndView("admin/driver/unclosed_orders/list-unclosed-users-orders");
        mav.addObject("userOrders", userOrderRepository.findAllByIsClosedFalse());
        driverService.getAvailableDriversWithCarTypeOnDate(userOrderRepository.getById(8L).getCarType(), userOrderRepository.getById(8L).getDateOfDispatch(),  userOrderRepository.getById(8L).getDuration());
        return mav;
    }

    @GetMapping("/showDriverOrderAssignForm")
    private ModelAndView showDriverOrderAssignForm(@RequestParam Long userOrderId) {
        ModelAndView mav = new ModelAndView("admin/driver/unclosed_orders/assign-driver-to-user-order");
        UserOrder userOrder = userOrderRepository.getById(userOrderId);
        mav.addObject("userOrder", userOrder);
        mav.addObject("freeDrivers", driverService.getAvailableDriversWithCarTypeOnDate(userOrder.getCarType(), userOrder.getDateOfDispatch(), userOrder.getDuration()));
        return mav;
    }

    @GetMapping("/assignDriver")
    private String assignDriver(@RequestParam Long userOrderId, @RequestParam Long driverId) {
        UserOrder userOrder = userOrderRepository.getById(userOrderId);
        userOrder.setIsClosed(true);
        Driver driver = driverRepository.getById(driverId);
        DriverOrder driverOrder = new DriverOrder();
        driverOrder.setUserOrder(userOrder);
        driverOrder.setDriver(driver);
        userOrderRepository.save(userOrder);
        driverOrderRepository.save(driverOrder);
        return "redirect:list";
    }

}
