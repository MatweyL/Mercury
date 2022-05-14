package com.example.germes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/main")
    public ModelAndView showMainPage() {
        ModelAndView mav = new ModelAndView("main");
        return mav;
    }

    @GetMapping("/")
    public ModelAndView showStartPage() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}
