package com.crm.service;

import java.util.List;

import com.crm.dto.CreateUserRequest;
import com.crm.dto.LoginRequest;
import com.crm.dto.UserResponse;


public interface UserService {
	
	String login(LoginRequest request);
	
	List<UserResponse> getAllUsers();

	UserResponse getUserById(Long id);
	
	UserResponse createUser(CreateUserRequest request);
	
	
}
