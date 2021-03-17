package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UsersController {
    @Autowired
    private UserService userService;

    // GET метод index по адресу /users
    @GetMapping("/admin")
    public String index(Model model) {
        // из DAO получаем всех user, пакуем в модель
        model.addAttribute("users", userService.index());
        return "users/index";
    }

    // GET метод show по адресу /users/:id
    @GetMapping("/admin/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        // из DAO получаем одного user по id, пакуем в модель
        model.addAttribute("user", userService.show(id));
        return "users/show";
    }

    // GET метод newUser по адресу /users/new
    @GetMapping("admin/new")
    public String newUser(@ModelAttribute("user") User user) {

        return "users/new";
    }

    // POST метод create по адресу /users
    @PostMapping("/admin")
    public String create(User user) {
        userService.save(user);

        return "redirect:/admin";
    }

    // GET метод edit users/:id/edit
    @GetMapping("admin/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "users/edit";
    }

    // PATCH метод update users/:id
    @PatchMapping("/admin/{id}")
    public String update(@PathVariable("id") Long id, User user) {
        userService.update(id, user);

        return "redirect:/admin";
    }

    // DELETE метод delete users/:id
    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);

        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String printIfUser(ModelMap model, Principal principal) {
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        System.out.println(principal);
        return "users/show";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "pages/login";
    }
}
