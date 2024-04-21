package com.cardosojl.models.dtos;

import java.io.Serializable;

import java.util.List;
import java.util.stream.Collectors;

import com.cardosojl.models.User;
import com.cardosojl.models.UserInterface;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO<P> implements Serializable, UserInterface {

	private static final long serialVersionUID = 6026062986300437308L;
	
	private Long id;
	private String email;
	private String name;
	private String Password;
	private Boolean accontNonExpired;
	private Boolean accontNonLocked;
	private Boolean credentialsNonExpired;
	private Boolean enabled;
	private P permissions;
	private String role = null;
	
	public UserDTO() {}
	
	public UserDTO(User u) {
		this.id = u.getId();
		this.name = u.getName();
		this.email = u.getEmail();
		this.accontNonExpired = u.getAccountNonExpired();
		this.accontNonLocked = u.getAccountNonLocked();
		this.credentialsNonExpired = u.getCredentialsNonExpired();
		this.enabled = u.getEnabled();
		this.role = (String) u.getPermissions().stream().map(p -> new PermissionDTO(p).getDescription()).collect(Collectors.joining(", "));
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	public String getPassword() {
		return Password;
	}
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public void setPassword(String password) {
		Password = password;
	}
	
	public Boolean getAccontNonExpired() {
		return accontNonExpired;
	}
	
	public void setAccontNonExpired(Boolean accontNonExpired) {
		this.accontNonExpired = accontNonExpired;
	}
	
	public Boolean getAccontNonLocked() {
		return accontNonLocked;
	}
	
	public void setAccontNonLocked(Boolean accontNonLocked) {
		this.accontNonLocked = accontNonLocked;
	}
	
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@JsonIgnore
	public P getPermissions() {
		return permissions;
	}
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public void setPermissions(P permissions) {
		this.permissions = permissions;
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
		this.role =  ((List<String>) roles).stream().collect(Collectors.joining(", "));	
	}
	
	@JsonIgnore
	@Override
	public Boolean isAnAcceptableRole() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
