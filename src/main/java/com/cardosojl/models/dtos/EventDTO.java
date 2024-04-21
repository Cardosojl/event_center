package com.cardosojl.models.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cardosojl.models.Event;
import com.cardosojl.models.EventStatusENUM;


public class EventDTO<E, O> implements Serializable {

	private static final long serialVersionUID = 5592193115393585817L;
	private Long id;
	private String name;
	private EventStatusENUM status;
	private LocalDateTime date;
	private String description;
	private String eventRequest;
	private E eventType;
	private O organizer;
	private List<MerchantDTO> merchants = new ArrayList<>();
	
	public EventDTO(Long id, String name, EventStatusENUM status, LocalDateTime date, String description, String eventRequest, E eventType,
			O organizer) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.date = date;
		this.description = description;
		this.eventRequest = eventRequest;
		this.eventType = eventType;
		this.organizer = organizer;
	}
	
	@SuppressWarnings({ "unchecked" })
	public EventDTO(Event event) {
		super();
		this.id = event.getId();
		this.name = event.getName();
		this.status = event.getStatus();
		this.date = event.getDate();
		if (event.getDescription() != null) this.description = event.getDescription();
		this.eventRequest = event.getEventRequest();
		this.eventType = (E) new EventTypeDTO(event.getEventType());
		this.organizer = (O) new OrganizerDTO(event.getOrganizer());
		this.merchants = event.getMerchants().stream().map(m -> new MerchantDTO(m)).toList();
	}
	
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public EventStatusENUM getStatus() {
		return status;
	}

	public void setState(EventStatusENUM status) {
		this.status = status;
	}

	public O getOrganizer() {
		return organizer;
	}
	
	public void setOrganizer(O organizer) {
		this.organizer = organizer;
	}
	
	public String getEventRequest() {
		return eventRequest;
	}
	
	public void setEventRequest(String eventRequest) {
		this.eventRequest = eventRequest;
	}	
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public E getEventType() {
		return eventType;
	}
	
	public void setEventType(E eventType) {
		this.eventType = eventType;
	}

	public List<MerchantDTO> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<MerchantDTO> merchants) {
		this.merchants = merchants;
	}	

}
