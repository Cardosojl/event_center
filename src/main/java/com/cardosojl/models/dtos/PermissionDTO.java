package com.cardosojl.models.dtos;

import java.io.Serializable;

import com.cardosojl.models.Permission;

public class PermissionDTO implements Serializable {

	private static final long serialVersionUID = 455634149752759731L;
	
	private Long id;
	private String description;
	
	public PermissionDTO() {}
	
	public PermissionDTO(Permission p) {
		this.id = p.getId();
		this.description = p.getDescription();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
