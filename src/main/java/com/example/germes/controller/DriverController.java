package com.example.germes.controller;

import com.example.germes.entity.Driver;
import com.example.germes.repo.DriverRepository;
import com.example.germes.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/driver")
public class DriverController {

    @Autowired
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping({"/list", "/"})
    public ModelAndView getAllDrivers() {
        ModelAndView mav = new ModelAndView("admin/driver/driver_creation/list-drivers");
        mav.addObject("drivers", driverService.findAll());
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

}
