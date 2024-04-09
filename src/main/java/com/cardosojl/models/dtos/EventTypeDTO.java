package com.cardosojl.models.dtos;

import java.io.Serializable;

import com.cardosojl.models.EventType;

public class EventTypeDTO implements Serializable {
	
	private static final long serialVersionUID = -7518147493914318079L;
	
	private Long id;
	private String type;
	
	public EventTypeDTO() {}
	
	public EventTypeDTO(Long id, String type) {
		this.id = id;
		this.type = type;
	}
	
	public EventTypeDTO(EventType event) {
		this.id = event.getId();
		this.type = event.getType();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
