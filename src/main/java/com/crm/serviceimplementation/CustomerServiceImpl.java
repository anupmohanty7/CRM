package com.crm.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.dto.CreateCustomerRequest;
import com.crm.dto.CustomerResponse;
import com.crm.entity.Customer;
import com.crm.exception.CustomerNotFoundException;
import com.crm.repository.CustomerRepository;
import com.crm.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerResponse CreateCustomer(CreateCustomerRequest request) {
		Customer customer = new Customer();

		customer.setFirstname(request.getFirstname());
		customer.setLastname(request.getLastname());
		customer.setEmail(request.getEmail());
		customer.setPhone(request.getPhone());
		customer.setCompany(request.getCompany());
		customer.setAddress(request.getAddress());
		customer.setStatus(request.getStatus());

		// Lead-related fields
		customer.setCustomerType(request.getCustomerType());
		customer.setLeadSource(request.getLeadSource());
		customer.setLeadStatus(request.getLeadStatus());

		Customer savedCustomer = customerRepository.save(customer);

		CustomerResponse response = new CustomerResponse();

		response.setId(savedCustomer.getId());
		response.setFirstname(savedCustomer.getFirstname());
		response.setLastname(savedCustomer.getLastname());
		response.setEmail(savedCustomer.getEmail());
		response.setPhone(savedCustomer.getPhone());
		response.setCompany(savedCustomer.getCompany());
		response.setAddress(savedCustomer.getAddress());
		response.setStatus(savedCustomer.getStatus());

		// Lead-related fields
		response.setCustomerType(savedCustomer.getCustomerType());
		response.setLeadSource(savedCustomer.getLeadSource());
		response.setLeadStatus(savedCustomer.getLeadStatus());

		response.setCreatedAt(savedCustomer.getCreatedAt());
		response.setUpdatedAt(savedCustomer.getUpdatedAt());

		return response;
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
	}

	@Override
	public Customer updateCustomer(Long id, Customer customer) {
		Customer oldData = getCustomerById(id);

		oldData.setFirstname(customer.getFirstname());
		oldData.setLastname(customer.getLastname());
		oldData.setEmail(customer.getEmail());
		oldData.setPhone(customer.getPhone());
		oldData.setCompany(customer.getCompany());
		oldData.setAddress(customer.getAddress());
		oldData.setStatus(customer.getStatus());

		// Lead-related fields
		oldData.setCustomerType(customer.getCustomerType());
		oldData.setLeadSource(customer.getLeadSource());
		oldData.setLeadStatus(customer.getLeadStatus());

		return customerRepository.save(oldData);
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer customer = getCustomerById(id);
		customerRepository.delete(customer);
	}
}