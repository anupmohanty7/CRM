package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByEmail(String email);
}
