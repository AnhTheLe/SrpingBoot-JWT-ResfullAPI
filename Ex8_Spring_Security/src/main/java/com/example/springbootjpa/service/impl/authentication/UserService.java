package com.example.springbootjpa.service.impl.authentication;


import com.example.springbootjpa.entity.auth.Role;
import com.example.springbootjpa.entity.auth.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);

}
