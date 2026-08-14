package com.crm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.dto.CreateCustomFieldRequest;
import com.crm.dto.CustomFieldResponse;
import com.crm.service.CustomFieldService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/custom-fields")
@SecurityRequirement(name = "bearerAuth")
public class CustomFieldController {

    private final CustomFieldService customFieldService;

    public CustomFieldController(CustomFieldService customFieldService) {
        this.customFieldService = customFieldService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CustomFieldResponse createCustomField(
            @Valid @RequestBody CreateCustomFieldRequest request) {

        return customFieldService.createCustomField(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCustomField(@PathVariable Long id) {

        customFieldService.DeleteCustomField(id);
    }
}