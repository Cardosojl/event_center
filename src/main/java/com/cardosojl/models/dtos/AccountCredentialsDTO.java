package com.cardosojl.models.dtos;

import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialsDTO implements Serializable{

	private static final long serialVersionUID = -5752582334716283948L;

	private String email;
	private String name;
	private String password;
	
	public AccountCredentialsDTO(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountCredentialsDTO other = (AccountCredentialsDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}
	
	
	
	

}
