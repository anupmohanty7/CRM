package com.crm.serviceimplementation;

import org.springframework.stereotype.Service;

import com.crm.dto.DashboardResponse;
import com.crm.enums.CustomerType;
import com.crm.enums.FollowupStatus;
import com.crm.enums.LeadStatus;
import com.crm.repository.CustomerRepository;
import com.crm.repository.FollowUpRepository;
import com.crm.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	private final CustomerRepository customerRepository;
	private final FollowUpRepository followUpRepository;

	public DashboardServiceImpl(CustomerRepository customerRepository,
			FollowUpRepository followUpRepository) {
		this.customerRepository = customerRepository;
		this.followUpRepository = followUpRepository;
	}

	@Override
	public DashboardResponse getDashboard() {

		DashboardResponse response = new DashboardResponse();

		// Customer statistics
		response.setTotalCustomers(customerRepository.count());

		response.setTotalLeads(
				customerRepository.countByCustomerType(CustomerType.LEAD)
		);

		response.setTotalActualCustomers(
				customerRepository.countByCustomerType(CustomerType.CUSTOMER)
		);

		// Lead statistics
		response.setNewLeads(
				customerRepository.countByLeadStatus(LeadStatus.NEW)
		);

		response.setQualifiedLeads(
				customerRepository.countByLeadStatus(LeadStatus.QUALIFIED)
		);

		response.setConvertedLeads(
				customerRepository.countByLeadStatus(LeadStatus.CONVERTED)
		);

		response.setLostLeads(
				customerRepository.countByLeadStatus(LeadStatus.LOST)
		);

		// Follow-up statistics
		response.setPendingFollowUps(
				followUpRepository.countByStatus(FollowupStatus.PENDING)
		);

		response.setSuccessfulFollowUps(
				followUpRepository.countByStatus(FollowupStatus.SUCCESS_CLOSED)
		);

		response.setFailedFollowUps(
				followUpRepository.countByStatus(FollowupStatus.FAILED_CLOSED)
		);

		return response;
	}
}