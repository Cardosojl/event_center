package com.cardosojl.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cardosojl.exceptions.exceptions.ResourceNotFoundException;
import com.cardosojl.models.EventType;
import com.cardosojl.models.dtos.EventTypeDTO;
import com.cardosojl.repositories.EventTypeRepository;

@Service
public class EventTypeService {
	
	@Autowired
	EventTypeRepository repository;
	Logger logger = Logger.getLogger(EventTypeService.class.getName());
	
	public List<EventTypeDTO> findAll(Pageable pageable) {
		logger.info("Searching for all Event Types");
		Page<EventType> eventTypePages = repository.findAll(pageable);
		Page<EventTypeDTO> eventTypesDTO = eventTypePages.map(e -> new EventTypeDTO(e));
		return eventTypesDTO.getContent();
	}
	
	public EventTypeDTO findOne(Long id) {
		logger.info("Searching for a Type");
		EventType type = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return new EventTypeDTO(type);
	}
	
	public EventTypeDTO create(EventTypeDTO e) {
		logger.info("Creating a Type");
		EventType eventType = new EventType(e.getType());
		return new EventTypeDTO(repository.save(eventType));
	}
	
	public EventTypeDTO updateOne(EventTypeDTO e) {
		logger.info("Updating a Type");
		EventType type = repository.findById(e.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		if (e.getType() != null) type.setType(e.getType());
		return new EventTypeDTO(repository.save(type));
	}
	
	public void deleteOne(Long id) {
		logger.info("Deleting a Type");
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.deleteById(id);
	}

}
