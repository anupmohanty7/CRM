package com.crm.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.entity.Customer;
import com.crm.entity.FollowUp;
import com.crm.exception.CustomerNotFoundException;
import com.crm.exception.FollowUpNotFoundException;
import com.crm.repository.CustomerRepository;
import com.crm.repository.FollowUpRepository;
import com.crm.service.FollowUpService;

@Service
public class FollowUpServiceImpl implements FollowUpService{
	private final FollowUpRepository followUpRepository;
	private final CustomerRepository customerRepository;
    public FollowUpServiceImpl(FollowUpRepository followUpRepository,CustomerRepository customerRepository) {
    	this.customerRepository=customerRepository;
    	this.followUpRepository=followUpRepository;
    }
	@Override
	public FollowUp createFollowUp(FollowUp followUp) {
		Customer customer=customerRepository.findById(followUp.getCustomer().getId()).orElseThrow(()->new CustomerNotFoundException("Customer not found"));
		followUp.setCustomer(customer);
		return followUpRepository.save(followUp);
	}
	@Override
	public List<FollowUp> getAllFollowUps() {
	    return followUpRepository.findAll();
	}
	@Override
	public FollowUp getFollowUpById(Long id) {
	    return followUpRepository.findById(id)
	            .orElseThrow(() ->
	                    new FollowUpNotFoundException("Follow-up not found"));
	}
	@Override
	public FollowUp updateFollowUp(Long id, FollowUp followUp) {

	    FollowUp existing = getFollowUpById(id);

	    Customer customer = customerRepository.findById(
	            followUp.getCustomer().getId())
	            .orElseThrow(() ->
	                    new CustomerNotFoundException("Customer not found"));

	    existing.setCustomer(customer);
	    existing.setFollowUpDateTime(followUp.getFollowUpDateTime());
	    existing.setStatus(followUp.getStatus());
	    existing.setNotes(followUp.getNotes());

	    return followUpRepository.save(existing);
	}
	@Override
	public void deleteFollowUp(Long id) {

	    FollowUp followUp = getFollowUpById(id);

	    followUpRepository.delete(followUp);
	}
    
}
