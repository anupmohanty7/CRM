package com.crm.validation;

import com.crm.dto.CreateCustomerRequest;
import com.crm.enums.CustomerType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerTypeValidator implements ConstraintValidator<CustomerTypeValidation, CreateCustomerRequest> {

	@Override
	public boolean isValid(CreateCustomerRequest value, ConstraintValidatorContext context) {
		if (value == null) {
            return true;
        }

        if (value.getCustomerType() == null) {
            return true;
        }

        if (value.getCustomerType() == CustomerType.LEAD) {
            return value.getLeadStatus() != null
                    && value.getLeadSource() != null;
        }

        if (value.getCustomerType() == CustomerType.CUSTOMER) {
            return value.getLeadStatus() == null
                    && value.getLeadSource() == null;
        }

        return false;
    }
}
