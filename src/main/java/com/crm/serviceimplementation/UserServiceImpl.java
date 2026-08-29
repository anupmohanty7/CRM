package com.crm.serviceimplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crm.dto.ChangeRoleRequest;
import com.crm.dto.ChangeStatusRequest;
import com.crm.dto.CreateAccountRequest;
import com.crm.dto.CreateUserRequest;
import com.crm.dto.LoginRequest;
import com.crm.dto.UpdateUserRequest;
import com.crm.dto.UserResponse;
import com.crm.entity.Role;
import com.crm.entity.User;
import com.crm.enums.UserStatus;
import com.crm.exception.InvalidPasswordException;
import com.crm.exception.RoleNotFoundException;
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

		if (user.getStatus() != UserStatus.ACTIVE) {
		    throw new RuntimeException("User account is not active");
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
                        new RoleNotFoundException("Role not found"));

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
	@Override
	public UserResponse updateUser(Long id, UpdateUserRequest request) {

	    User user = userRepository.findById(id)
	            .orElseThrow(() ->
	                    new UserNotFoundException("User not found"));

	    user.setFirstname(request.getFirstName());
	    user.setLastname(request.getLastName());
	    user.setEmail(request.getEmail());

	    User updatedUser = userRepository.save(user);

	    UserResponse response = new UserResponse();

	    response.setId(updatedUser.getId());
	    response.setFirstName(updatedUser.getFirstname());
	    response.setLastName(updatedUser.getLastname());
	    response.setEmail(updatedUser.getEmail());
	    response.setRole(updatedUser.getRole().getRoleName());
	    response.setStatus(updatedUser.getStatus());

	    return response;
	}
	
	@Override
	public void deleteUser(Long id) {

	    User user = userRepository.findById(id)
	            .orElseThrow(() ->
	                    new UserNotFoundException("User not found"));

	    userRepository.delete(user);
	}

	@Override
	public UserResponse changeUserRole(Long id, ChangeRoleRequest request) {

	    User user = userRepository.findById(id)
	            .orElseThrow(() ->
	                    new UserNotFoundException("User not found"));

	    Role role = roleRepository.findByRoleName(request.getRole())
	            .orElseThrow(() ->
	                    new RoleNotFoundException("Role not found"));

	    user.setRole(role);

	    User updatedUser = userRepository.save(user);

	    UserResponse response = new UserResponse();

	    response.setId(updatedUser.getId());
	    response.setFirstName(updatedUser.getFirstname());
	    response.setLastName(updatedUser.getLastname());
	    response.setEmail(updatedUser.getEmail());
	    response.setRole(updatedUser.getRole().getRoleName());
	    response.setStatus(updatedUser.getStatus());

	    return response;
	}
	@Override
	public UserResponse changeUserStatus(Long id, ChangeStatusRequest request) {

	    User user = userRepository.findById(id)
	            .orElseThrow(() ->
	                    new UserNotFoundException("User not found"));

	    user.setStatus(request.getStatus());

	    User updatedUser = userRepository.save(user);

	    UserResponse response = new UserResponse();

	    response.setId(updatedUser.getId());
	    response.setFirstName(updatedUser.getFirstname());
	    response.setLastName(updatedUser.getLastname());
	    response.setEmail(updatedUser.getEmail());
	    response.setRole(updatedUser.getRole().getRoleName());
	    response.setStatus(updatedUser.getStatus());

	    return response;
	}

	@Override
	public UserResponse registerFirstAccount(CreateAccountRequest request) {

	    // Only the first account can be created through registration
	    if (userRepository.count() > 0) {
	        throw new RuntimeException(
	                "An account already exists. Please contact the administrator."
	        );
	    }

	    // Check if passwords match
	    if (!request.getPassword().equals(request.getConfirmPassword())) {
	        throw new RuntimeException("Passwords do not match");
	    }

	    // Get ADMIN role from database
	    Role adminRole = roleRepository.findByRoleName("ADMIN")
	            .orElseThrow(() ->
	                    new RoleNotFoundException("ADMIN role not found"));

	    // Create first user
	    User user = new User();

	    user.setFirstname(request.getFirstName());
	    user.setLastname(request.getLastName());
	    user.setEmail(request.getEmail());

	    // Encrypt password
	    user.setPassword(passwordEncoder.encode(request.getPassword()));

	    // First account automatically becomes ADMIN
	    user.setRole(adminRole);

	    // First account is active
	    user.setStatus(UserStatus.ACTIVE);

	    // Save user
	    User savedUser = userRepository.save(user);

	    // Convert entity to response DTO
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
