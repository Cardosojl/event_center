package com.cardosojl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardosojl.models.dtos.UserDTO;
import com.cardosojl.services.UserService;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO store(@RequestBody UserDTO u) {
		UserDTO userDTO = service.create(u);
		return userDTO;
	}

}
