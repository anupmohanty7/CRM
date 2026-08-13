package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.entity.FollowUp;
import com.crm.enums.FollowupStatus;

public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {
	long countByStatus(FollowupStatus status);
}
