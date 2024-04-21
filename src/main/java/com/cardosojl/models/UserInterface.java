package com.cardosojl.models;

public interface UserInterface {
	
	Long getId();
	void setId(Long id);
	
	String getName();
	void setName(String name);
	
	String getEmail();
	void setEmail(String email);
	
	String getRole();
	void setRole(String role);
	
	Boolean isAnAcceptableRole();
}
