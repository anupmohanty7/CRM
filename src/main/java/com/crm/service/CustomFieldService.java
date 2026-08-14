package com.crm.service;

import com.crm.dto.CreateCustomFieldRequest;
import com.crm.dto.CustomFieldResponse;

public interface CustomFieldService {

	public CustomFieldResponse createCustomField(CreateCustomFieldRequest request);
	
	public void DeleteCustomField(Long id);
}
