package com.cardosojl.models.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class TokenDTO implements Serializable {

	private static final long serialVersionUID = 3145041024053881443L;
	
	private String username;
	private String email;
	private String role;
	private Boolean authenticated;
	private Date created;
	private Date expirantion;
	private String accessToken;
	private String refreshToken;
	
	public TokenDTO() {}
	
	public TokenDTO(String username, String email, String role, Boolean authenticated, Date created, Date expirantion,
			String accessToken, String refreshToken) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
		this.authenticated = authenticated;
		this.created = created;
		this.expirantion = expirantion;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpirantion() {
		return expirantion;
	}

	public void setExpirantion(Date expirantion) {
		this.expirantion = expirantion;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessToken, authenticated, created, email, expirantion, refreshToken, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenDTO other = (TokenDTO) obj;
		return Objects.equals(accessToken, other.accessToken) && Objects.equals(authenticated, other.authenticated)
				&& Objects.equals(created, other.created) && Objects.equals(email, other.email)
				&& Objects.equals(expirantion, other.expirantion) && Objects.equals(refreshToken, other.refreshToken)
				&& Objects.equals(username, other.username);
	}
	
	
	
	
	
	

}
