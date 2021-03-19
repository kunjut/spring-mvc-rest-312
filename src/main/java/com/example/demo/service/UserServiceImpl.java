package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl
        implements UserService, UserDetailsService {
    @Autowired
    private UserDAO userDAO;

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
        userDAO.save(user);
    }

    @Override
    public void update(Long id, User updatedUser) {
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

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    // «Пользователь» – это просто Object.
    // В большинстве случаев он может быть приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", email));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    //Из коллекции ролей делаем коллекцию Authorities
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }
}
