package com.example.demo.dao;

import com.example.demo.models.Role;

public interface RoleDao {
    Role findByRoleName(String name);
}
