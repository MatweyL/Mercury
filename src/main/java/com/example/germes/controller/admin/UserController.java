package com.example.germes.controller.admin;

import com.example.germes.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

    @Autowired
    private final AdminUserService adminUserService;

    public UserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping({"/list", "/"})
    public ModelAndView getAllDrivers() {
        ModelAndView mav = new ModelAndView("admin/user/list-users");
        mav.addObject("users", adminUserService.findAllUsers());
        return mav;
    }

    @GetMapping("/showUserDetails")
    public ModelAndView getUserDetails(@RequestParam Long userId) {
        ModelAndView mav = new ModelAndView("admin/user/user-details");
        mav.addObject("user", adminUserService.getUserById(userId));
        mav.addObject("userOrders", adminUserService.getUserUserOrders(userId));
        return mav;
    }
    @GetMapping("/showUserOrderDetails")
    public ModelAndView getUserOrderDetails(@RequestParam Long userOrderId) {
        ModelAndView mav = new ModelAndView("admin/user/user-order-details");
        mav.addObject("userOrder", adminUserService.getUserOrder(userOrderId));
        return mav;
    }

}
