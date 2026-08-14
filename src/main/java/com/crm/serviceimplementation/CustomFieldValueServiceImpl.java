package com.crm.serviceimplementation;

import org.springframework.stereotype.Service;

import com.crm.dto.CreateCustomFieldValueRequest;
import com.crm.dto.CustomFieldValueResponse;
import com.crm.entity.CustomField;
import com.crm.entity.CustomFieldValue;
import com.crm.entity.Customer;
import com.crm.exception.CustomFielsNotFoundException;
import com.crm.exception.CustomerNotFoundException;
import com.crm.repository.CustomFieldRepository;
import com.crm.repository.CustomFieldValueRepository;
import com.crm.repository.CustomerRepository;
import com.crm.service.CustomFieldValueService;

@Service
public class CustomFieldValueServiceImpl implements CustomFieldValueService {

    private final CustomFieldValueRepository customFieldValueRepository;
    private final CustomerRepository customerRepository;
    private final CustomFieldRepository customFieldRepository;

    public CustomFieldValueServiceImpl(
            CustomFieldValueRepository customFieldValueRepository,
            CustomerRepository customerRepository,
            CustomFieldRepository customFieldRepository) {

        this.customFieldValueRepository = customFieldValueRepository;
        this.customerRepository = customerRepository;
        this.customFieldRepository = customFieldRepository;
    }

    @Override
    public CustomFieldValueResponse createCustomFieldValue(
            Long customerId,
            CreateCustomFieldValueRequest request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        CustomField customField = customFieldRepository
                .findById(request.getCustomFieldId())
                .orElseThrow(() ->
                        new CustomFielsNotFoundException("Custom field not found"));

        CustomFieldValue customFieldValue = new CustomFieldValue();

        customFieldValue.setCustomer(customer);
        customFieldValue.setCustomField(customField);
        customFieldValue.setValue(request.getValue());

        CustomFieldValue savedValue =
                customFieldValueRepository.save(customFieldValue);

        CustomFieldValueResponse response =
                new CustomFieldValueResponse();

        response.setId(savedValue.getId());
        response.setCustomerId(savedValue.getCustomer().getId());
        response.setCustomFieldId(savedValue.getCustomField().getId());
        response.setCustomFieldName(
                savedValue.getCustomField().getName());
        response.setValue(savedValue.getValue());

        return response;
    }
}