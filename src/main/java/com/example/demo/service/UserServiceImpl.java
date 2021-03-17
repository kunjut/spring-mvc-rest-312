package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> index() {
        return userDAO.index();
    }

    @Override
    public User show(Long id) {
        return userDAO.show(id);
    }

    @Override
    public void save(User user) {
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userDAO.save(user);
    }

    @Override
    public void update(Long id, User updatedUser) {
        updatedUser.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));

        userDAO.update(id, updatedUser);
    }

    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Override
    public User getUserByName(String username) {
        return userDAO.getUserByName(username);
    }
}
