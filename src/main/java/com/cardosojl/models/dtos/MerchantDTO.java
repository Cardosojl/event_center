package com.cardosojl.models.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.cardosojl.models.User;
import com.cardosojl.models.UserInterface;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MerchantDTO implements Serializable, UserInterface {

	private static final long serialVersionUID = -6104058244724960527L;
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private String role;
	
	public MerchantDTO() {}		


	public MerchantDTO(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public MerchantDTO(UserInterface u) {
		this.id = u.getId();
		this.name = u.getName();
		this.email = u.getEmail();
		this.role = u.getRole();
	}
	
	public MerchantDTO(User u) {
		this.id = u.getId();
		this.name = u.getName();
		this.email = u.getEmail();
		this.role = (String) u.getPermissions().stream().map(p -> new PermissionDTO(p).getDescription()).collect(Collectors.joining(", "));
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String getRole() {
		return this.role;
	}

	@Override
	public void setRole(String role) {
		this.role = role;		
	}
	
	public void setRole(List<String> roles) {
		this.role =  roles.stream().collect(Collectors.joining(", "));	
	}



	@JsonIgnore
	@Override
	public Boolean isAnAcceptableRole() {
		return this.getRole().contains("MERCHANT");
	}

}
