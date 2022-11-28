package com.example.merkury.controller.user;

import com.example.merkury.entity.dto.UserDataDto;
import com.example.merkury.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user/profile")
public class UserProfileController {

    @Autowired
    private final UserDataService userDataService;

    public UserProfileController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping({""})
    private ModelAndView getProfile() {
        ModelAndView mav = new ModelAndView("user/user-profile");
        mav.addObject("userDataDto", userDataService.getUserDataDto());
        mav.addObject("user", userDataService.getCurrentUser());
        return mav;
    }

    @GetMapping({"/update"})
    private ModelAndView updateProfile() {
        ModelAndView mav = new ModelAndView("user/update-user-profile");
        mav.addObject("userDataDto", userDataService.getUserDataDto());
        mav.addObject("user", userDataService.getCurrentUser());
        return mav;
    }

    @PostMapping({"/update/save"})
    private String saveUserProfile(@ModelAttribute UserDataDto userDataDto) {
        userDataService.update(userDataDto);
        return "redirect:/user/profile";
    }

}
