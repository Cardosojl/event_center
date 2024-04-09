package com.cardosojl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardosojl.models.dtos.AccountCredentialsDTO;
import com.cardosojl.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authServices;
	
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsDTO data) {
		if (data == null || data.getEmail() == null || data.getEmail().isBlank() || data.getPassword() == null || data.getPassword().isBlank()) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client Request");
		var token = authServices.signin(data);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client Request");
		return token;
	}
	

}
