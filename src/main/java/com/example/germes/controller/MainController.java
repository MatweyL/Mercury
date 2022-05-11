package com.example.germes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping
    public ModelAndView showMainPage() {
        ModelAndView mav = new ModelAndView("я");
        return mav;
    }
}
