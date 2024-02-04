package com.cardosojl.models;

import java.io.Serializable;

public class EventType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
