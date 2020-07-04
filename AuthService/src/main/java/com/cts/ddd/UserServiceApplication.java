package com.cts.ddd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.ddd.application.UserService;
import com.cts.feedback.user.User;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String security="Welcome";
		User user = new User("admin", security, "admin", "Gowthami");
		userService.addUser(user);
		
		user = new User("user01", security, "user","User1");
		userService.addUser(user);
		
		user = new User("user02", security, "user", "User2");
		userService.addUser(user);
	}
}
