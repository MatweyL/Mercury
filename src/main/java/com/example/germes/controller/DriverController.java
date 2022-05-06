package com.example.germes.controller;

import com.example.germes.entity.Driver;
import com.example.germes.repo.CarRepository;
import com.example.germes.repo.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DriverController {

    @Autowired
    private final DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping({"/list", "/"})
    public ModelAndView getAllDrivers() {
        ModelAndView mav = new ModelAndView("list-drivers");
        mav.addObject("drivers", driverRepository.findAll());
        return mav;
    }

    @GetMapping("/addDriverForm")
    public ModelAndView addDriverForm() {
        ModelAndView mav = new ModelAndView("add-driver-form");
        Driver newDriver = new Driver();
        mav.addObject("driver", newDriver);
        return mav;
    }

    @PostMapping("/saveDriver")
    public String saveDriver(@ModelAttribute Driver driver) {
        driver.setIsBusy(false);
        driver.getCar().setDriver(driver);
        driverRepository.save(driver);
        return "redirect:/list";
    }

}
