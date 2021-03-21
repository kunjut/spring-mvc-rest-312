package com.example.demo.dao;

import com.example.demo.models.User;

import java.util.List;

public interface UserDAO {
    List<User> index();

    User show(Long id);

    void save(User user);

    void update(User updatedUser);

    void delete(Long id);

    User getUserByName(String username);

    User getUserByEmail(String email);
}
