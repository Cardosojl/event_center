package com.cardosojl.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event implements Serializable {

	private static final long serialVersionUID = -6103236089260037048L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 45, nullable = false)
	private String name;
	@Column(nullable = false)
	private LocalDateTime date;
	@Column(length = 800, nullable = false)
	private String eventRequest;
	@Column(length = 800, nullable = false)	
	private String description;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organizer_id", nullable = false)
	private Organizer organizer;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_type_id", nullable = false)
	private EventType eventType;
	@ManyToMany
	@JoinTable(
			name = "merchants_events", joinColumns = @JoinColumn(name = "events_id"),
			inverseJoinColumns =  @JoinColumn(name = "merchants_id")
			)
	private List<Merchant> merchants = new ArrayList<>();
	
	public Event() {}
		
	public Event(String name, LocalDateTime date, String eventRequest, String description, Organizer organizer,
			EventType eventType) {
		super();
		this.name = name;
		this.date = date;
		this.eventRequest = eventRequest;
		this.description = description;
		this.organizer = organizer;
		this.eventType = eventType;
	}
	
	public String getEventRequest() {
		return eventRequest;
	}
	public void setEventRequest(String eventRequest) {
		this.eventRequest = eventRequest;
	}
	public Organizer getOrganizer() {
		return organizer;
	}
	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
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
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}	
	

}
