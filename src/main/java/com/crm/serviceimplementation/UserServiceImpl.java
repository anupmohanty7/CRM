package com.crm.serviceimplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crm.dto.CreateUserRequest;
import com.crm.dto.LoginRequest;
import com.crm.dto.UserResponse;
import com.crm.entity.Role;
import com.crm.entity.User;
import com.crm.enums.UserStatus;
import com.crm.exception.InvalidPasswordException;
import com.crm.exception.UserNotFoundException;
import com.crm.repository.RoleRepository;
import com.crm.repository.UserRepository;
import com.crm.security.JwtUtil;
import com.crm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil,RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil=jwtUtil;
		this.roleRepository=roleRepository;
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

	@Override
	public List<UserResponse> getAllUsers() {

	    List<User> users = userRepository.findAll();

	    List<UserResponse> responses = new ArrayList<>();

	    for (User user : users) {

	        UserResponse response = new UserResponse();

	        response.setId(user.getId());
	        response.setFirstName(user.getFirstname());
	        response.setLastName(user.getLastname());
	        response.setEmail(user.getEmail());
	        response.setRole(user.getRole().getRoleName());
	        response.setStatus(user.getStatus());

	        responses.add(response);
	    }

	    return responses;
	}

	@Override
	public UserResponse getUserById(Long id) {
		
		User user=userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
		
		UserResponse response = new UserResponse();
		
		 response.setId(user.getId());
	        response.setFirstName(user.getFirstname());
	        response.setLastName(user.getLastname());
	        response.setEmail(user.getEmail());
	        response.setRole(user.getRole().getRoleName());
	        response.setStatus(user.getStatus());
		
		return response;
	}

	@Override
	public UserResponse createUser(CreateUserRequest request) {
		
		Role role = roleRepository.findByRoleName(request.getRole())
                .orElseThrow(() ->
                        new RuntimeException("Role not found"));

        User user = new User();

        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setEmail(request.getEmail());

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        user.setPassword(encodedPassword);

        user.setRole(role);
        user.setStatus(UserStatus.ACTIVE);

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setFirstName(savedUser.getFirstname());
        response.setLastName(savedUser.getLastname());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole().getRoleName());
        response.setStatus(savedUser.getStatus());

        return response;
	}

}
