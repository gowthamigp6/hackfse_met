package com.cts.feedback.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "user")
@Data
public class User {

	@Id
	private String userId;
	private String password;
	private String role;
	private String userName;
	private String email;
	
	public User() {
	}

	public User(String userId, String password, String role, String userName,String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
		this.userName = userName;
		this.email =  email;
	}
	
	
}
