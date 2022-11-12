package com.example.germes.controller.user;

import com.example.germes.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
