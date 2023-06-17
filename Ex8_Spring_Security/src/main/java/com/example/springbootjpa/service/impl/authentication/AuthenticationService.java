package com.example.springbootjpa.service.impl.authentication;


import com.example.springbootjpa.dto.auth.AuthenticationRequest;
import com.example.springbootjpa.dto.auth.AuthenticationResponse;
import com.example.springbootjpa.dto.auth.RegisterRequest;
import com.example.springbootjpa.entity.auth.ERole;
import com.example.springbootjpa.entity.auth.Role;

import com.example.springbootjpa.entity.auth.User;
import com.example.springbootjpa.repository.RoleCustomRepo;
import com.example.springbootjpa.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserServiceImpl userService;

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final RoleCustomRepo roleCustomRepo;

    public ResponseEntity<String> register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
//                .roles(new HashSet<>(Arrays.asList(new Role(ERole.ROLE_USER))))
                .build();
//        var user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        userService.saveUser(user);
        userService.addRoleToUser(user.getUsername(), String.valueOf(ERole.ROLE_USER));
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
        log.info("User registered successfully: {}", user.getUsername());
        return ResponseEntity.ok("User registered successfully: " + user.getUsername());
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        List<Role> role = null;
        if(user != null){
            role = roleCustomRepo.getRole(user);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<Role> set = new HashSet<>();
        role.stream().forEach(c-> set.add(new Role(c.getName())));
        user.setRoles(set);
        set.stream().forEach(c-> authorities.add(new SimpleGrantedAuthority(c.getName())));
        var jwtToken = jwtService.generateToken(user,authorities);
        var refreshToken = jwtService.generateRefreshToken(user, authorities);
        log.info("User authenticated successfully: {}", user.getUsername());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String userEmail;
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        refreshToken = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(refreshToken);
//        if (userEmail != null) {
//            var user = this.userRepository.findByEmail(userEmail)
//                    .orElseThrow();
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                var accessToken = jwtService.generateToken(user);
//                // Không cần thực hiện thu hồi token và lưu trữ token mới vào database
//                var authResponse = AuthenticationResponse.builder()
//                        .token(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
    }

}
