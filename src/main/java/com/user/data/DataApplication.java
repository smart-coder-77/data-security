package com.user.data;

import com.user.data.entity.AppUser;
import com.user.data.entity.Role;
import com.user.data.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class DataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			System.out.println("Git learnings");
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_PRIMARY_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SECONDARY_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_PROCESSOR"));

			userService.saveUser(new AppUser(null,"Subham Choudhury","subham","1234",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Jhonny Depp","jhonny","828070",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Jeff Bezos","jeffy","943924",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Elon Musk","elon","17456",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Krishna Murari","krishna","123456",new ArrayList<>()));

			userService.addRoleToUser("subham","ROLE_USER");
			userService.addRoleToUser("jhonny","ROLE_SECONDARY_ADMIN");
			userService.addRoleToUser("jeffy","ROLE_USER");
			userService.addRoleToUser("elon","ROLE_MANAGER");
			userService.addRoleToUser("krishna","ROLE_SECONDARY_ADMIN");
			userService.addRoleToUser("jhonny","ROLE_PROCESSOR");
			userService.addRoleToUser("jeffy","ROLE_PRIMARY_ADMIN");
			userService.addRoleToUser("subham","ROLE_PROCESSOR");

		};
	}
}
