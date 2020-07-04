package com.cts.ddd.infrastructure;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cts.feedback.user.User;



public interface UserRepository extends UserDetailsService {

	User findUserId(String userId);
	
	User addUser(User user);
	
	void deleteUser(String userId);
	
	Iterable<User> getAllUser();
	
}