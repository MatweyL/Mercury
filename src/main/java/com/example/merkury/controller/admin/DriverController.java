package com.example.merkury.controller.admin;

import com.example.merkury.service.DriverDataService;
import com.example.merkury.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/driver")
public class DriverController {

    @Autowired
    private final DriverService driverService;

    @Autowired
    private final DriverDataService driverDataService;

    public DriverController(DriverService driverService, DriverDataService driverDataService) {
        this.driverService = driverService;
        this.driverDataService = driverDataService;
    }

    @GetMapping({"/list", "/"})
    public ModelAndView getAllDrivers() {
        ModelAndView mav = new ModelAndView("admin/driver/drivers/list-drivers");
        mav.addObject("driversDataDto", driverDataService.getAll());
        return mav;
    }

    @GetMapping({"/profile"})
    public ModelAndView getDriver(@RequestParam Long driverId) {
        ModelAndView mav = new ModelAndView("admin/driver/drivers/driver-profile");
        mav.addObject("driverDataDto", driverDataService.getDriverDataDtoByDriverId(driverId));
        return mav;
    }

    @GetMapping({"/profile/orders"})
    public ModelAndView getDriverOrders(@RequestParam Long driverId) {
        ModelAndView mav = new ModelAndView("admin/driver/drivers/list-driver-orders");
        mav.addObject("driverOrders", driverDataService.getDriverOrders(driverId));
        return mav;
    }

    @GetMapping("/verifyDriver")
    public String verifyDriver(@RequestParam Long driverId, Boolean isVerified) {
        driverDataService.setDriverIsVerified(driverId, isVerified);
        return "redirect:list";
    }

    @GetMapping("/activateDriver")
    public String activateDriver(@RequestParam Long driverId, Boolean isActive) {
        driverDataService.setDriverIsActive(driverId, isActive);
        return "redirect:list";
    }


}
