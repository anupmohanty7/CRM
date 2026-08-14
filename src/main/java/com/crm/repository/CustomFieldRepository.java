package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.entity.CustomField;

public interface CustomFieldRepository extends JpaRepository<CustomField,Long>{
	
}
