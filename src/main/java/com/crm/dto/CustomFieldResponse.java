package com.crm.dto;

import com.crm.enums.CustomFieldType;

public class CustomFieldResponse {

	private Long id;
	
	private String name;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
