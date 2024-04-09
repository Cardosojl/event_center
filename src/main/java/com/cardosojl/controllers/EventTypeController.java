package com.cardosojl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cardosojl.models.dtos.EventTypeDTO;
import com.cardosojl.services.EventTypeService;

@RestController
@RequestMapping("/api/event_type/V1")
public class EventTypeController {
	
	@Autowired
	private EventTypeService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EventTypeDTO>> index(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "2") Integer limit) {
		Pageable pageable = PageRequest.of(page, limit);
		List<EventTypeDTO> events = service.findAll(pageable);
		return ResponseEntity.ok(events);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EventTypeDTO show(@PathVariable(value = "id") Long id) {
		EventTypeDTO eventDTO = service.findOne(id);
		return eventDTO;
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public EventTypeDTO store(@RequestBody EventTypeDTO event) {
		EventTypeDTO eventDTO = service.create(event);
		return eventDTO;
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public EventTypeDTO update(@RequestBody EventTypeDTO event) {
		EventTypeDTO eventDTO = service.updateOne(event);
		return eventDTO;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.deleteOne(id);
		return ResponseEntity.noContent().build();
	}

}
