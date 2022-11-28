package com.example.merkury.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView showStartPage() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}
