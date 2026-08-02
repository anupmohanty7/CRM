package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
	
}
