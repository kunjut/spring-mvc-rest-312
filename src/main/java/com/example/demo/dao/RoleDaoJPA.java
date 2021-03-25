package com.example.demo.dao;

import com.example.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDaoJPA extends JpaRepository<Role, Long> {

}
