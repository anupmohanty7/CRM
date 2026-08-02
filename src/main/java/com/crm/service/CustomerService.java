package com.crm.service;

import java.util.List;

import com.crm.dto.CreateCustomerRequest;
import com.crm.dto.CustomerResponse;
import com.crm.entity.Customer;

public interface CustomerService {
	CustomerResponse CreateCustomer(CreateCustomerRequest request);
	List<Customer> getAllCustomer();
	Customer getCustomerById(Long id);
	Customer updateCustomer(Long id, Customer customer);
	void deleteCustomer(Long id);
	
}
