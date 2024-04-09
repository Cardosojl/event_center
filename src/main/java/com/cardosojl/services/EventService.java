package com.cardosojl.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cardosojl.models.Event;
import com.cardosojl.models.EventType;
import com.cardosojl.models.Organizer;
import com.cardosojl.models.dtos.EventDTO;
import com.cardosojl.models.dtos.EventTypeDTO;
import com.cardosojl.models.dtos.OrganizerDTO;
import com.cardosojl.repositories.EventRepository;
import com.cardosojl.repositories.EventTypeRepository;
import com.cardosojl.repositories.OrganizerRepository;
import com.cardosojl.exceptions.exceptions.ResourceNotFoundException;

@Service
public class EventService {
	
	@Autowired
	EventRepository repository;
	
	@Autowired
	EventTypeRepository eventTypeRepository;
	
	@Autowired
	OrganizerRepository organizerRepository;
	
	Logger logger = Logger.getLogger(EventService.class.getName());
	
	public List<EventDTO<EventTypeDTO, OrganizerDTO>> findAll(Pageable pageable) {
		logger.info("Searching for all");
		Page<Event> eventPages = repository.findAll(pageable);
		Page<EventDTO<EventTypeDTO, OrganizerDTO>> eventsDTO = eventPages.map(e -> new EventDTO<EventTypeDTO, OrganizerDTO>(e));		
		return eventsDTO.getContent();		
	}
	
	public EventDTO<EventTypeDTO, OrganizerDTO> findOne(Long id) {
		logger.info("Searching for an Event");
		Event event = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return new EventDTO<EventTypeDTO, OrganizerDTO>(event);
	}
	
	public List<EventDTO<EventTypeDTO, OrganizerDTO>> findOneByDate(String dateString) {
		logger.info("Searching for Events by Date");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Event> events = repository.findEventByDate(date);
		List<EventDTO<EventTypeDTO, OrganizerDTO>> eventsDTO = events.stream().map(e -> new EventDTO<EventTypeDTO, OrganizerDTO>(e)).toList();
		return eventsDTO;
	}
	
	public EventDTO<EventTypeDTO, OrganizerDTO> create(EventDTO<Long, Long> e) {
		logger.info("Creating an Event");
		Organizer organizer = organizerRepository.findById(e.getOrganizer()).orElseThrow(() -> new ResourceNotFoundException("No organizer found for this ID"));
		EventType eventType = eventTypeRepository.findById(e.getEventType()).orElseThrow(() -> new ResourceNotFoundException("No eventType found for this ID"));
		Event event = new Event(e.getName(), e.getDate(), e.getEventRequest(), e.getDescription(), organizer, eventType);		
		return new EventDTO<EventTypeDTO, OrganizerDTO>(repository.save(event));
	}
	
	public EventDTO<EventTypeDTO, OrganizerDTO> updateOne(EventDTO<Long, Long> e) {
		logger.info("Updating an Event");
		Event event = repository.findById(e.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		if (e.getName() != null) event.setName(e.getName());
		if (e.getDate() != null) event.setDate(e.getDate());
		if (e.getDescription() != null) event.setDescription(e.getDescription());
		if (e.getEventType() != null) {
			EventType eventType = eventTypeRepository.findById(e.getEventType()).orElseThrow(() -> new ResourceNotFoundException("No eventType found for this ID"));
			event.setEventType(eventType);
		}	
		return new EventDTO<EventTypeDTO, OrganizerDTO>(event);
	}
	
	public void deleteOne(Long id) {
		logger.info("Deleting an Event");
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.deleteById(id);
	}
	
	

}
