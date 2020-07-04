package com.cts.ddd.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.ddd.application.UserService;

import com.cts.ddd.infrastructure.UserRepository;
import com.cts.feedback.user.User;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserId(String userId) {
		return userRepository.findUserId(userId);
	}

	@Override
	public User addUser(User user) {
		return userRepository.addUser(user);
	}

	@Override
	public void deleteUser(String userId) {
		 userRepository.deleteUser(userId);
	}

	@Override
	public Iterable<User> getAllUser() {
		return userRepository.getAllUser();
	}

}
