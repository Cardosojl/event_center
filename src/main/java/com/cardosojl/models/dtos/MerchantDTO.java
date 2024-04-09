package com.cardosojl.models.dtos;

import java.io.Serializable;

import com.cardosojl.models.Merchant;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MerchantDTO implements Serializable{

	private static final long serialVersionUID = -6104058244724960527L;
	
	private Long id;
	private String name;
	private String password;
	private String phone;
	private String email;
	
	public MerchantDTO() {}	
	
		


	public MerchantDTO(Long id, String name, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public MerchantDTO(Merchant merchant) {
		super();
		this.id = merchant.getId();
		this.name = merchant.getName();
		this.phone = merchant.getPhone();
		this.email = merchant.getEmail();
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
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

}
