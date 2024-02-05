package com.cardosojl.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardosojl.models.Event;
import com.cardosojl.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	EventRepository repository;
	Logger logger = Logger.getLogger(EventService.class.getName());
	
	public List<Event> findAll() {
		logger.info("Searching for all");
		List<Event> events = repository.findAll();
		return events;		
	}
	
	public Event findOne(Integer id) {
		logger.info("Searching for an Event");
		Event event = repository.findById(id).orElseThrow(() -> new Error());
		return event;
	}
	
	public Event create(Event e) {
		logger.info("Creating an Event");
		return repository.save(e);
	}
	
	public Event update(Event e) {
		logger.info("Updating an Event");
		Event event = repository.findById(e.getId()).orElseThrow(() -> new Error());
		if (e.getName() != null) event.setName(e.getName());
		if (e.getDate() != null) event.setDate(e.getDate());
		if (e.getDescription() != null) event.setDescription(e.getDescription());
		if (e.getType() != null) event.setId(null);		
		return event;
	}
	
	public void deleteOne(Integer id) {
		logger.info("Deleting an Event");
		repository.deleteById(id);
	}
	
	

}
