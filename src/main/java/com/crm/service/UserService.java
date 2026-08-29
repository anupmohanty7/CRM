package com.crm.service;

import java.util.List;

import com.crm.dto.ChangeRoleRequest;
import com.crm.dto.ChangeStatusRequest;
import com.crm.dto.CreateAccountRequest;
import com.crm.dto.CreateUserRequest;
import com.crm.dto.LoginRequest;
import com.crm.dto.UpdateUserRequest;
import com.crm.dto.UserResponse;

public interface UserService {

    String login(LoginRequest request);

    UserResponse registerFirstAccount(CreateAccountRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse createUser(CreateUserRequest request);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);

    UserResponse changeUserRole(Long id, ChangeRoleRequest request);

    UserResponse changeUserStatus(Long id, ChangeStatusRequest request);
}