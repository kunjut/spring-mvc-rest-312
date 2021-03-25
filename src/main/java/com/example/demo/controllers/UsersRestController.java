package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRestController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.index();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.show(id);
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
