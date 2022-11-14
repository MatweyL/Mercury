package com.example.germes.controller.driver;

import com.example.germes.entity.dto.DriverDataDto;
import com.example.germes.service.DriverDataService;
import com.example.germes.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/driver")
public class DriverUserController {

    @Autowired
    private final DriverDataService driverDataService;

    @Autowired
    private final UserOrderService userOrderService;


    public DriverUserController(DriverDataService driverDataService, UserOrderService userOrderService) {
        this.driverDataService = driverDataService;
        this.userOrderService = userOrderService;
    }

    @GetMapping({"/profile"})
    public ModelAndView getDriverProfile() {
        ModelAndView mav = new ModelAndView("driver/driver-profile");
        mav.addObject("driverDataDto", driverDataService.getDriverDataDto());
        return mav;
    }

    @GetMapping({"/profile/update"})
    public ModelAndView updateProfile() {
        ModelAndView mav = new ModelAndView("driver/update-driver-profile");
        mav.addObject("driverDataDto", driverDataService.getDriverDataDto());
        return mav;
    }

    @PostMapping({"/profile/update/save"})
    public String saveProfile(@ModelAttribute DriverDataDto driverDataDto) {
        driverDataService.update(driverDataDto);
        return "redirect:/driver/profile";
    }

    @GetMapping({"/orders"})
    public ModelAndView getDriverOrders() {
        ModelAndView mav = new ModelAndView("driver/list-driver-orders");
        mav.addObject("driverOrders", driverDataService.getCurrentDriverOrders());
        return mav;
    }

    @GetMapping({"/orders/order"})
    public ModelAndView getDriverOrder(Long userOrderId) {
        ModelAndView mav = new ModelAndView("driver/driver-order-details");
        mav.addObject("userOrder", userOrderService.getById(userOrderId));
        return mav;
    }


}
