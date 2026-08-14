package com.crm.service;

import com.crm.dto.CreateCustomFieldValueRequest;
import com.crm.dto.CustomFieldValueResponse;

public interface CustomFieldValueService {

    CustomFieldValueResponse createCustomFieldValue(
            Long customerId,
            CreateCustomFieldValueRequest request);
}