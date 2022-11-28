package com.example.merkury.controller.admin;

import com.example.merkury.entity.dto.CarTypePriceDto;
import com.example.merkury.service.CarTypePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/car")
public class CarTypePriceController {

    @Autowired
    private final CarTypePriceService carTypePriceService;

    public CarTypePriceController(CarTypePriceService carTypePriceService) {
        this.carTypePriceService = carTypePriceService;
    }

    @GetMapping({"/", "list"})
    public ModelAndView getAllCarTypePrices() {
        ModelAndView mav = new ModelAndView("admin/car/list-cars-prices");
        CarTypePriceDto carTypePriceDto = new CarTypePriceDto(carTypePriceService.findAllCarTypePrices());
        mav.addObject("form", carTypePriceDto);
        return mav;
    }

    @PostMapping("/updateCarTypePrices")
    public String updateCarTypePrices(@ModelAttribute CarTypePriceDto form) {
        carTypePriceService.saveAll(form.getCarTypePrices());
        return "redirect:list";
    }
}
