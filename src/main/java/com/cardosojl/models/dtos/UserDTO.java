package com.cardosojl.models.dtos;

import java.io.Serializable;
import java.util.List;

import com.cardosojl.models.Permission;
import com.cardosojl.models.User;

public class UserDTO<P> implements Serializable {

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
	
	public UserDTO() {}
	
	public UserDTO(User u) {
		this.id = u.getId();
		this.name = u.getName();
		this.email = u.getEmail();
		this.accontNonExpired = u.getAccountNonExpired();
		this.accontNonLocked = u.getAccountNonLocked();
		this.credentialsNonExpired = u.getCredentialsNonExpired();
		this.enabled = u.getEnabled();
		this.permissions = (P) u.getPermissions().stream().map(p -> new PermissionDTO(p)).toString();
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
	
	public String getPassword() {
		return Password;
	}
	
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
	
	public P getPermissions() {
		return permissions;
	}
	
	public void setPermissions(P permissions) {
		this.permissions = permissions;
	}
	
	
	

}
