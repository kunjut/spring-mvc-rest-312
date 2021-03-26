package com.example.demo.service;

import com.example.demo.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    List<User> index();

    User show(Long id);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    void save(User user);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    void update(User updatedUser);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    void delete(Long id);

    User getUserByName(String username);

    UserDetails loadUserByUsername(String username);

    User getUserByEmail(String email);
}
