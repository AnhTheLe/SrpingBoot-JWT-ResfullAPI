package com.example.spring_security.service;

import com.example.spring_security.entity.ERole;
import com.example.spring_security.entity.Role;
import com.example.spring_security.entity.User;
import com.example.spring_security.repository.RoleRepository;
import com.example.spring_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    List<User> findAllUser(){
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByEmail(username).orElse(null);
        Optional<Role> role = roleRepository.findByName(roleName);
        if (user != null && role.isPresent()) {
            if (user.getRoles() == null) {
                user.setRoles(new HashSet<>()); // Khởi tạo roles là một HashSet mới nếu roles là null
            }
            user.getRoles().add(role.get());
        }
    }


}
