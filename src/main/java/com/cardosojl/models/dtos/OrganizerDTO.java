package com.cardosojl.models.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cardosojl.models.Event;
import com.cardosojl.models.User;
import com.cardosojl.models.UserInterface;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizerDTO implements Serializable, UserInterface {

	private static final long serialVersionUID = 5038429125658715985L;
	
	private Long id;
	private String name;
	private String password;
	private String email;
	private String role;
	private List<Event> events =  new ArrayList<Event>();
	
	public OrganizerDTO() {}
	
	public OrganizerDTO(UserInterface u) {
		this.id = u.getId();
		this.name = u.getName();
		this.email = u.getEmail();
		this.role = u.getRole();
	}
	
	public OrganizerDTO(User u) {
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
	
	@JsonIgnore
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
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
		return this.getRole().contains("ORGANIZER");
	}	

}
