package com.crm.serviceimplementation;

import org.springframework.stereotype.Service;

import com.crm.dto.CreateCustomFieldRequest;
import com.crm.dto.CustomFieldResponse;
import com.crm.entity.CustomField;
import com.crm.exception.CustomFielsNotFoundException;
import com.crm.repository.CustomFieldRepository;
import com.crm.service.CustomFieldService;

@Service
public class CustomFieldServiceImpl implements CustomFieldService{

	private final CustomFieldRepository customFieldRepository;
	
	public CustomFieldServiceImpl(CustomFieldRepository customFieldRepository) {
		this.customFieldRepository=customFieldRepository;
	}
	
	@Override
	public CustomFieldResponse createCustomField(CreateCustomFieldRequest request) {
		
		CustomField customField= new CustomField();
		
		customField.setName(request.getName());
		customField.setType(request.getType());
		
		CustomField savedField=customFieldRepository.save(customField);
		
		CustomFieldResponse response =  new CustomFieldResponse();
		
		response.setId(savedField.getId());
		response.setName(savedField.getName());
		response.setType(savedField.getType());
		
		return response;
	}

	@Override
	public void DeleteCustomField(Long id) {
		
		if(customFieldRepository.existsById(id))
		{
			customFieldRepository.deleteById(id);
		}
		else {
			throw new CustomFielsNotFoundException("Field Not present");
		}
		
	}

}
