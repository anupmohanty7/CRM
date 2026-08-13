package com.crm.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.dto.ChangeRoleRequest;
import com.crm.dto.CreateUserRequest;
import com.crm.dto.UpdateUserRequest;
import com.crm.dto.UserResponse;
import com.crm.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public List<UserResponse> getAllUsers()
	{
		
		return userService.getAllUsers();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public UserResponse getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public UserResponse createUser(
	        @Valid @RequestBody CreateUserRequest request) {

	    return userService.createUser(request);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public UserResponse updateUser(
	        @PathVariable Long id,
	        @Valid @RequestBody UpdateUserRequest request) {

	    return userService.updateUser(id, request);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {

	    userService.deleteUser(id);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}/role")
	public UserResponse changeUserRole(
	        @PathVariable Long id,
	        @Valid @RequestBody ChangeRoleRequest request) {

	    return userService.changeUserRole(id, request);
	}  
}
