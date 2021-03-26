package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "security/login";
    }

    @GetMapping("/admin")
    public String testBS(Model model, Principal principal) {
        model.addAttribute("principal", userService.getUserByEmail(principal.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "index";
    }

    @GetMapping("/user")
    public String printIfUser(ModelMap model, Principal principal) {
        model.addAttribute("principal", userService.getUserByEmail(principal.getName()));
        return "show_bs";
    }

    @GetMapping("/learn")
    public String learn() {
        return "learn_fetch_api";
    }
}


