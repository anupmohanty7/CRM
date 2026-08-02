package com.crm.service;

import com.crm.dto.LoginRequest;


public interface UserService {
	
	String login(LoginRequest request);
}
