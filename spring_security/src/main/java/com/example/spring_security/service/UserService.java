package com.example.spring_security.service;

import com.example.spring_security.entity.ERole;
import com.example.spring_security.entity.Role;
import com.example.spring_security.entity.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);

}
