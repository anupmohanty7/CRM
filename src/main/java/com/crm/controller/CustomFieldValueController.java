package com.crm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.dto.CreateCustomFieldValueRequest;
import com.crm.dto.CustomFieldValueResponse;
import com.crm.service.CustomFieldValueService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
@SecurityRequirement(name = "bearerAuth")
public class CustomFieldValueController {

    private final CustomFieldValueService customFieldValueService;

    public CustomFieldValueController(
            CustomFieldValueService customFieldValueService) {

        this.customFieldValueService = customFieldValueService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{customerId}/custom-fields")
    public CustomFieldValueResponse createCustomFieldValue(
            @PathVariable Long customerId,
            @Valid @RequestBody CreateCustomFieldValueRequest request) {

        return customFieldValueService.createCustomFieldValue(
                customerId, request);
    }
}