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

import com.cardosojl.models.dtos.EventDTO;
import com.cardosojl.models.dtos.EventTypeDTO;
import com.cardosojl.models.dtos.MerchantDTO;
import com.cardosojl.models.dtos.OrganizerDTO;
import com.cardosojl.services.EventService;

@RestController
@RequestMapping("/api/event/V1")
public class EventController {
	@Autowired
	private EventService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EventDTO<EventTypeDTO, OrganizerDTO>>> index(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "2") Integer limit){
		Pageable pageable = PageRequest.of(page, limit);
		List<EventDTO<EventTypeDTO, OrganizerDTO>> events = service.findAll(pageable);
		return ResponseEntity.ok(events);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EventDTO<EventTypeDTO, OrganizerDTO> show(@PathVariable(value = "id") Long id) {
		EventDTO<EventTypeDTO, OrganizerDTO> event = service.findOne(id);		
		return event;
	}
	
	@GetMapping(value = "/findEventByDate/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EventDTO<EventTypeDTO, OrganizerDTO>> show(@PathVariable(value = "date") String date) {
		List<EventDTO<EventTypeDTO, OrganizerDTO>> event = service.findOneByDate(date);	
		return event;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EventDTO<EventTypeDTO, OrganizerDTO> store(@RequestBody EventDTO<Long, Long> event) {
		EventDTO<EventTypeDTO, OrganizerDTO> eventDTO = service.create(event);
		return eventDTO;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EventDTO<EventTypeDTO, OrganizerDTO> update(@RequestBody EventDTO<Long, Long> event) {
		EventDTO<EventTypeDTO, OrganizerDTO> eventDTO = service.updateOne(event);
		return eventDTO;
	}
	
	@PutMapping(value = "/addMerchant/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EventDTO<EventTypeDTO, OrganizerDTO> updateToAddMerchant(@PathVariable(value = "id") Long id, @RequestBody MerchantDTO merchant ){
		EventDTO<EventTypeDTO, OrganizerDTO> event = service.addMerchant(id, merchant.getId());
		return event;
	}
	
	@PutMapping(value = "/removeMerchant/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EventDTO<EventTypeDTO, OrganizerDTO> updateToRemoveMerchant(@PathVariable(value = "id") Long id, @RequestBody MerchantDTO merchant ){
		EventDTO<EventTypeDTO, OrganizerDTO> event = service.removeMerchant(id, merchant.getId());
		return event;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.deleteOne(id);
		return ResponseEntity.noContent().build();
	}

}
