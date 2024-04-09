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

import com.cardosojl.models.dtos.OrganizerDTO;
import com.cardosojl.services.OrganizerService;

@RestController
@RequestMapping("/api/organizer/V1")
public class OrganizerController {
	@Autowired
	OrganizerService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrganizerDTO>> index(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "2") Integer limit) {
		Pageable pageable = PageRequest.of(page, limit);
		List<OrganizerDTO> organizers = service.findAll(pageable);
		return ResponseEntity.ok(organizers);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OrganizerDTO show(@PathVariable(value = "id") Long id) {
		OrganizerDTO organizerDTO = service.findOne(id);
		return organizerDTO;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrganizerDTO store(@RequestBody OrganizerDTO o) {
		OrganizerDTO organizerDTO = service.create(o);
		return organizerDTO;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrganizerDTO update(@RequestBody OrganizerDTO o) {
		OrganizerDTO organizerDTO = service.updateOne(o);
		return organizerDTO;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.deleteOne(id);
		return ResponseEntity.noContent().build();
	}

}
