package com.crm.dto;

import com.crm.enums.CustomFieldType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCustomFieldRequest {

    @NotBlank
    private String name;

    @NotNull
    private CustomFieldType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomFieldType getType() {
        return type;
    }

    public void setType(CustomFieldType type) {
        this.type = type;
    }
}