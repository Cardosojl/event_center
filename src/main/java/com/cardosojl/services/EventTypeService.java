package com.cardosojl.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardosojl.exceptions.exceptions.ResourceNotFoundException;
import com.cardosojl.models.EventType;
import com.cardosojl.repositories.EventTypeRepository;

@Service
public class EventTypeService {
	
	@Autowired
	EventTypeRepository repository;
	Logger logger = Logger.getLogger(EventTypeService.class.getName());
	
	public List<EventType> findAll() {
		logger.info("Searching for all");
		List<EventType> types = repository.findAll();
		return types;
	}
	
	public EventType findOne(Integer id) {
		logger.info("Searching for a Type");
		EventType type = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return type;
	}
	
	public EventType create(EventType e) {
		logger.info("Creating a Type");
		return repository.save(e);
	}
	
	public EventType updateOne(EventType e) {
		logger.info("Updating a Type");
		EventType type = repository.findById(e.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		if (e.getType() != null) type.setType(e.getType());
		return type;
	}
	
	public void deleteOne(Integer id) {
		logger.info("Deleting a Type");
		repository.deleteById(id);
	}

}
