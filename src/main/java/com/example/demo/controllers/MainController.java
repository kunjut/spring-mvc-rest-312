package com.example.demo.controllers;

import com.example.demo.dao.RoleDaoJPA;
import com.example.demo.models.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;
    private final RoleService roleService;
    private final RoleDaoJPA roleDaoJPA;
    @Autowired
    public MainController(UserService userService, RoleService roleService, RoleDaoJPA roleDaoJPA) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleDaoJPA = roleDaoJPA;
    }

    @GetMapping("/admin")
    public String testBS(Model model, Principal principal, @ModelAttribute("user") User user) {
        model.addAttribute("users", userService.index());
        model.addAttribute("principal", userService.getUserByEmail(principal.getName()));
        model.addAttribute("_roles", roleService.getAllRoles());
        model.addAttribute("jpa_roles", roleDaoJPA.findAll());

//        return "bs/index_bs";
        return "bs/test";
    }

    @GetMapping("/user")
    public String printIfUser(ModelMap model, Principal principal, Authentication authentication) {
//        model.addAttribute("user", userService.getUserByName(principal.getName()));
        model.addAttribute("principal", userService.getUserByEmail(principal.getName()));

        return "bs/show_bs";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "pages/login";
    }

    @GetMapping("/learn")
    public String learn() {

        return "learn_fetch_api";
    }
}
