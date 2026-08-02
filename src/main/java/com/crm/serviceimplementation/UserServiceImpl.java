package com.crm.serviceimplementation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crm.dto.LoginRequest;
import com.crm.entity.User;
import com.crm.exception.InvalidPasswordException;
import com.crm.exception.UserNotFoundException;
import com.crm.repository.UserRepository;
import com.crm.service.UserService;

import com.crm.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil=jwtUtil;
	}

	@Override
	public String login(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail());

		System.out.println("Returned User: " + user);

		if (user == null) {
			throw new UserNotFoundException("User not found");
		}

		if (!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
			throw new InvalidPasswordException("Incorrect Password");
		}

		return jwtUtil.generateToken(user.getEmail());
	}

}
