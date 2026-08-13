package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entity.Customer;
import com.crm.enums.CustomerType;
import com.crm.enums.LeadStatus;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	long countByCustomerType(CustomerType customerType);

	long countByLeadStatus(LeadStatus leadStatus);

}