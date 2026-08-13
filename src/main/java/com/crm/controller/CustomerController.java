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

import com.crm.dto.CreateCustomerRequest;
import com.crm.dto.CustomerResponse;
import com.crm.entity.Customer;
import com.crm.service.CustomerService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
@SecurityRequirement(name = "bearerAuth")

public class CustomerController {
	private final CustomerService customerService;
	public CustomerController(CustomerService customerService)
	{
		this.customerService=customerService;
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
	@PostMapping
	public CustomerResponse create(@Valid @RequestBody CreateCustomerRequest request) {
	 
	    return customerService.CreateCustomer(request);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
	@GetMapping
	public List<Customer> getAll(){
		return customerService.getAllCustomer();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
	@GetMapping("/{id}")
	public Customer getCustById(@PathVariable Long id){
		return customerService.getCustomerById(id);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
	@PutMapping("/{id}")
	public Customer update(@PathVariable Long id,@RequestBody Customer customer){
		return customerService.updateCustomer(id,customer);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		 customerService.deleteCustomer(id);
	}
}
