package com.cardosojl.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardosojl.models.UserInterface;
import com.cardosojl.models.dtos.OrganizerDTO;
import com.cardosojl.services.UserService;

@RestController
@RequestMapping("/api/organizer/V1")
public class OrganizerController {
	@Autowired
	UserService service;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OrganizerDTO show(@PathVariable(value = "id") Long id) {
		OrganizerDTO organizerDTO = service.findOneOrganizer(id);
		return organizerDTO;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrganizerDTO update(@RequestBody UserInterface o) {
		OrganizerDTO organizerDTO = service.updateOneOrganizer(o);
		return organizerDTO;
	}

}
