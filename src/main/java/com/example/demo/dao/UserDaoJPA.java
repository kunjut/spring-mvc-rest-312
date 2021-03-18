package com.example.demo.dao;

import com.example.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDaoJPA extends JpaRepository<Role, Long> {
    // помогает из селекта правильно получить роли
}
