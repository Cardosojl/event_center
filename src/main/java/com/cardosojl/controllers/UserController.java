package com.cardosojl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.cardosojl.models.dtos.UserDTO;
import com.cardosojl.services.UserService;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {
	@Autowired
	private UserService service;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> index(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "2") Integer limit){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			Pageable pageable = PageRequest.of(page, limit);
			List<UserDTO<List<String>>> events = service.findAll(pageable);
			return ResponseEntity.ok(events);
		}
		return ResponseEntity.status(401).build();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>store(@RequestBody UserDTO<Integer> u) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ADMIN")) && u.getPermissions() == 1) {
			return ResponseEntity.status(401).build();
		}
		UserDTO<List<String>> userDTO = service.create(u);
		return ResponseEntity.ok(userDTO);
		
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> show(@PathVariable(value= "id") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
		UserDTO<List<String>> userDTO = service.findOne(id);
		return ResponseEntity.ok(userDTO);
		}
		return ResponseEntity.status(401).build();
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody UserDTO<Integer> u) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			UserDTO<String> userDTO = service.updateOne(u);
			return ResponseEntity.ok(userDTO);
		}
		return ResponseEntity.status(401).build();		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?>  delete(@PathVariable(value = "id") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			service.deleteOne(id);
			return ResponseEntity.noContent().build();			
		}
		return ResponseEntity.status(401).build();
	}
	
	

}
