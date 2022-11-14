package com.example.germes.controller.admin;

import com.example.germes.entity.Driver;
import com.example.germes.service.DriverDataService;
import com.example.germes.service.DriverService;
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
        ModelAndView mav = new ModelAndView("admin/driver/driver_creation/list-drivers");
        mav.addObject("driversDataDto", driverDataService.getAll());
        return mav;
    }

    @GetMapping("/addDriverForm")
    public ModelAndView addDriverForm() {
        ModelAndView mav = new ModelAndView("admin/driver/driver_creation/add-driver-form");
        Driver newDriver = new Driver();
        mav.addObject("driver", newDriver);
        return mav;
    }

    @PostMapping("/saveDriver")
    public String saveDriver(@ModelAttribute Driver driver) {
        driverService.setDriverIsBusy(driver,false);
        driverService.save(driver);
        return "redirect:list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long driverId) {
        ModelAndView mav = new ModelAndView("admin/driver/driver_creation/update-driver-form");
        Driver driver = driverService.getById(driverId);
        mav.addObject("driver", driver);
        return mav;
    }

    @PostMapping("/updateDriver")
    public String updateDriver(@ModelAttribute Driver driver) {
        driverService.save(driver);
        return "redirect:list";
    }

    @GetMapping("/deleteDriver")
    public String deleteDriver(@RequestParam Long driverId) {
        driverService.deleteById(driverId);
        return "redirect:list";
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
