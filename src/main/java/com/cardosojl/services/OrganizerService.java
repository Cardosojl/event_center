package com.cardosojl.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cardosojl.exceptions.exceptions.ResourceNotFoundException;
import com.cardosojl.models.Organizer;
import com.cardosojl.models.dtos.OrganizerDTO;
import com.cardosojl.repositories.OrganizerRepository;

@Service
public class OrganizerService {
	@Autowired
	OrganizerRepository repository;
	Logger logger = Logger.getLogger(OrganizerService.class.getName());
	
	public List<OrganizerDTO> findAll(Pageable pageable) {
		logger.info("Searching for all Organizers");
		Page<Organizer> organizerPages = repository.findAll(pageable);
		Page<OrganizerDTO> organizersDTO = organizerPages.map(o -> new OrganizerDTO(o));
		return organizersDTO.getContent();
	}
	
	public OrganizerDTO findOne(Long id) {
		logger.info("Searching for an Organizer");
		Organizer organizer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return new OrganizerDTO(organizer);
	}
	
	public OrganizerDTO create(OrganizerDTO o) {
		logger.info("Creating an Organizer");
		Organizer organizer = new Organizer(o.getName(), o.getPhone(), o.getEmail(), o.getPassword());
		return new OrganizerDTO(repository.save(organizer));
	}
	
	public OrganizerDTO updateOne(OrganizerDTO o) {
		logger.info("Updating an Organizer");
		Organizer organizer = repository.findById(o.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		if (o.getName() != null) organizer.setName(o.getName());
		if (o.getEmail() != null) organizer.setEmail(o.getEmail());
		if (o.getPhone() != null) organizer.setPhone(o.getPhone());
		//list
		//password
		return new OrganizerDTO(organizer);
	}
	
	public void deleteOne(Long id) {
		logger.info("Deleting an Organizer");
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.deleteById(id);
	}
	

}
