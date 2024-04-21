package com.cardosojl.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@Enumerated(EnumType.STRING)
	 @Column(columnDefinition = "ENUM('under_analysis', 'approved', 'rejected')' DEFAULT 'under_analysis'", nullable = false)
	private EventStatusENUM status;
	@Column(nullable = false)
	private LocalDateTime date;
	@Column(length = 800, nullable = false)
	private String eventRequest;
	@Column(length = 800, nullable = false)	
	private String description;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organizer_id", nullable = false)
	private User organizer;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_type_id", nullable = false)
	private EventType eventType;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "merchants_events", joinColumns = @JoinColumn(name = "id_event"),
			inverseJoinColumns =  @JoinColumn(name = "id_merchant")
			)
	private List<User> merchants = new ArrayList<>();
	
	public Event() {}
		
	public Event(String name, EventStatusENUM status, LocalDateTime date, String eventRequest, String description, User organizer,
			EventType eventType) {
		super();
		this.name = name;
		this.status = status;
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
	
	public User getOrganizer() {
		return organizer;
	}
	
	public void setOrganizer(User organizer) {
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
	
	public EventStatusENUM getStatus() {
		return status;
	}

	public void setStatus(EventStatusENUM status) {
		this.status = status;
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

	public List<User> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<User> merchants) {
		this.merchants = merchants;
	}
	
	public void addMerchant(User merchant) {
		this.merchants.add(merchant);
	}
	
	public void removeMerchant(User merchant) {
		this.setMerchants(this.merchants.stream().filter(m -> m.getId() != merchant.getId()).toList());
	}
	

}
