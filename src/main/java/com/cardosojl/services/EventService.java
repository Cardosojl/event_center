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
import com.cardosojl.models.EventStatusENUM;
import com.cardosojl.models.EventType;
import com.cardosojl.models.User;
import com.cardosojl.models.dtos.EventDTO;
import com.cardosojl.models.dtos.EventTypeDTO;
import com.cardosojl.models.dtos.MerchantDTO;
import com.cardosojl.models.dtos.OrganizerDTO;
import com.cardosojl.repositories.EventRepository;
import com.cardosojl.repositories.EventTypeRepository;
import com.cardosojl.repositories.UserRepository;
import com.cardosojl.exceptions.exceptions.InvalidOperationException;
import com.cardosojl.exceptions.exceptions.ResourceNotFoundException;

@Service
public class EventService {
	
	@Autowired
	EventRepository repository;
	
	@Autowired
	EventTypeRepository eventTypeRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
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
		User organizer = userRepository.findById(e.getOrganizer()).orElseThrow(() -> new ResourceNotFoundException("No user found for this ID"));
		if (!(new OrganizerDTO(organizer).isAnAcceptableRole())) {
			throw new ResourceNotFoundException("No organizer found for this ID");
		}
		EventType eventType = eventTypeRepository.findById(e.getEventType()).orElseThrow(() -> new ResourceNotFoundException("No eventType found for this ID"));
		
		Event event = new Event(e.getName(), EventStatusENUM.UNDER_ANALYSIS, e.getDate(), e.getEventRequest(), e.getDescription(), organizer, eventType);		
		return new EventDTO<EventTypeDTO, OrganizerDTO>(repository.save(event));
	}
	
	public EventDTO<EventTypeDTO, OrganizerDTO> updateOne(EventDTO<Long, Long> e) {
		logger.info("Updating an Event");
		Event event = repository.findById(e.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		if (e.getName() != null) event.setName(e.getName());
		if (e.getStatus() != null) event.setStatus(e.getStatus());
		if (e.getDate() != null) event.setDate(e.getDate());
		if (e.getDescription() != null) event.setDescription(e.getDescription());
		if (e.getEventType() != null) {
			EventType eventType = eventTypeRepository.findById(e.getEventType()).orElseThrow(() -> new ResourceNotFoundException("No eventType found for this ID"));
			event.setEventType(eventType);
		}	
		return new EventDTO<EventTypeDTO, OrganizerDTO>(repository.save(event));
	}
	
	public EventDTO<EventTypeDTO, OrganizerDTO> addMerchant(Long eventId,Long merchantId){
		logger.info("Add a Merchant");
		Event event = repository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("No eventType found for this ID"));
		if (event.getMerchants().size() == 3) {
			throw new InvalidOperationException("Number of merchants is complete");
		}
		if (event.getMerchants().stream().anyMatch(m -> m.getId().equals(merchantId))) {
			throw new InvalidOperationException("This Merchant is already participating in this event");
		}
		User merchant = userRepository.findById(merchantId).orElseThrow(() -> new ResourceNotFoundException("No user found for this ID"));
		if (!(new MerchantDTO(merchant).isAnAcceptableRole())) {
			throw new ResourceNotFoundException("No merchant found for this ID");
		}
		event.addMerchant(merchant);
		return new EventDTO<EventTypeDTO, OrganizerDTO>(repository.save(event));
	}
	
	public EventDTO<EventTypeDTO, OrganizerDTO> removeMerchant(Long eventId,Long merchantId){
		logger.info("Removing a Merchant");
		Event event = repository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("No eventType found for this ID"));
		if (event.getMerchants().size() == 0) {
			throw new InvalidOperationException("There are no Merchants to remove");
		}
		User merchant = userRepository.findById(merchantId).orElseThrow(() -> new ResourceNotFoundException("No user found for this ID"));
		if (!(new MerchantDTO(merchant).isAnAcceptableRole())) {
			throw new ResourceNotFoundException("No merchant found for this ID");
		}
		event.removeMerchant(merchant);
		return new EventDTO<EventTypeDTO, OrganizerDTO>(repository.save(event));
	}
	
	public void deleteOne(Long id) {
		logger.info("Deleting an Event");
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.deleteById(id);
	}
	
	

}
