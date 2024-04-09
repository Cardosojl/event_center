package com.cardosojl.models.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cardosojl.models.Event;
import com.cardosojl.models.Organizer;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizerDTO implements Serializable {

	private static final long serialVersionUID = 5038429125658715985L;
	
	private Long id;
	private String name;
	private String password;
	private String phone;
	private String email;
	private List<Event> events =  new ArrayList<Event>();
	
	public OrganizerDTO() {}
	
	public OrganizerDTO(Organizer o) {
		this.id = o.getId();
		this.name = o.getName();
		this.email = o.getEmail();
		this.phone = o.getPhone();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
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
