package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UsersController {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String index(Model model) {
        // из DAO получаем всех user, пакуем в модель
        model.addAttribute("users", userService.index());
        return "users/index";
    }

    @GetMapping("/admin/{id}")
    public String show(@PathVariable("id") Long id, Model model, Principal principal, Authentication authentication) {
        // из DAO получаем одного user по id, пакуем в модель
        model.addAttribute("user", userService.show(id));

//        printForMePrincipal(principal, authentication);

        return "users/show";
    }

    @GetMapping("admin/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("_roles", roleService.getAllRoles());

        return "users/new";
    }

    @PostMapping("/admin")
    public String create(User user) {
        userService.save(user);

        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.show(id));
        model.addAttribute("_roles", roleService.getAllRoles());

        return "users/edit";
    }

//    @PatchMapping("/admin/{id}") при переезде на boot перестал работать
    @PostMapping("/admin/edit/{id}")
    public String update(@PathVariable("id") Long id, User user) {
        userService.update(id, user);

        return "redirect:/admin";
    }

//    @DeleteMapping("/admin/{id}") при переезде на boot перестал работать
    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);

        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String printIfUser(ModelMap model, Principal principal, Authentication authentication) {
//        model.addAttribute("user", userService.getUserByName(principal.getName()));
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));

//        printForMePrincipal(principal, authentication);

        return "users/show";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "pages/login";
    }

//    public void printForMePrincipal(Principal principal, Authentication authentication) {
//        System.out.println(principal);
//        System.out.println(userService.getUserByName(principal.getName()));
//        System.out.println(userService.getUserByName(principal.getName()).getRoles());
//        System.out.println(authentication.getName());
//        System.out.println(authentication.getAuthorities());
//    }
}
