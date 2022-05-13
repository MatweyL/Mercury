package com.example.germes.controller;

import com.example.germes.dto.UserDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public ModelAndView showRegistrationForm() {
        UserDto userDto = new UserDto();
        ModelAndView mav = new ModelAndView("auth/registration");
        mav.addObject("user", userDto);
        return mav;
    }

}
