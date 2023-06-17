package com.example.springbootjpa;

//import com.example.springbootjpa.console.ConsoleController;
import com.example.springbootjpa.entity.auth.Role;
import com.example.springbootjpa.entity.auth.User;
import com.example.springbootjpa.service.impl.authentication.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SpringBootJpaApplication {
//	@Autowired
//	ConsoleController consoleController ;
//	https://sentayho.com.vn/dto-la-gi.html

//	public SpringBootJpaApplication(ApplicationContext context) {
//		this.context = context;
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

//	public void run(String... args) throws Exception {
//		ConsoleController consoleController = context.getBean(ConsoleController.class);
//		consoleController.printConsole();
//	}
//@Bean
//CommandLineRunner run(UserServiceImpl userService) {
//	return args ->{
//		userService.saveRole(new Role("ROLE_USER"));
//		userService.saveRole(new Role("ROLE_ADMIN"));
//		userService.saveRole(new Role("ROLE_MANAGER"));
//
//		userService.saveUser(new User("TheAnh", "anh@gamil.com","123456" ));
//		userService.saveUser(new User("TheAnh1", "the@gmail.com","123456"));
//		userService.saveUser(new User("TheAnh2", "theanh@gmail.com","123456"));
//
//		userService.addRoleToUser("anh@gmail.com", "ROLE_USER");
//		userService.addRoleToUser("the@gmail.com", "ROLE_USER");
//		userService.addRoleToUser("theanh@gmail.com", "ROLE_USER");
//		userService.addRoleToUser("the@gmail.com", "ROLE_ADMIN");
//		userService.addRoleToUser("theanh@gmail.com", "ROLE_ADMIN");
//	};
//
//}
}
