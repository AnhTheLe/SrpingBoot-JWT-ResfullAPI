package com.example.spring_security;

import com.example.spring_security.entity.User;
import com.example.spring_security.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

		@Bean
		CommandLineRunner run(UserServiceImpl userService) {
		return args ->{
			userService.saveRole(new com.example.spring_security.entity.Role("ROLE_USER"));
			userService.saveRole(new com.example.spring_security.entity.Role("ROLE_ADMIN"));
			userService.saveRole(new com.example.spring_security.entity.Role("ROLE_MANAGER"));

			userService.saveUser(new User("TheAnh", "anh@gamil.com","123456" ));
			userService.saveUser(new User("TheAnh1", "the@gmail.com","123456"));
			userService.saveUser(new User("TheAnh2", "theanh@gmail.com","123456"));

			userService.addRoleToUser("anh@gmail.com", "ROLE_USER");
			userService.addRoleToUser("the@gmail.com", "ROLE_USER");
			userService.addRoleToUser("theanh@gmail.com", "ROLE_USER");
			userService.addRoleToUser("the@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUser("theanh@gmail.com", "ROLE_ADMIN");
		};

	}

}
