package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.entity.CustomFieldValue;

public interface CustomFieldValueRepository
        extends JpaRepository<CustomFieldValue, Long> {

}