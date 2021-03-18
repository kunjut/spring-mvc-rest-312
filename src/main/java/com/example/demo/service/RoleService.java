package com.example.demo.service;

import com.example.demo.models.Role;

import java.util.List;

public interface RoleService {
    Role findByRoleName(String name);

    List<Role> getAllRoles();
}
