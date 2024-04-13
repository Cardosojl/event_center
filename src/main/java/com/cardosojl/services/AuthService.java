package com.cardosojl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cardosojl.models.User;
import com.cardosojl.models.dtos.AccountCredentialsDTO;
import com.cardosojl.models.dtos.TokenDTO;
import com.cardosojl.repositories.UserRepository;
import com.cardosojl.security.jwt.JwtTokenProvider;

@Service
public class AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserRepository repository;
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialsDTO data) {
		try {
			var username = data.getEmail();	
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			User user = repository.findByUsername(username);
			var tokenResponse = new TokenDTO();
			if (user != null) {
				tokenResponse = tokenProvider.createAccessToken(username, user.getName(), user.getRoles());				
			} else {
				throw new UsernameNotFoundException("Email " + username + " not found!");
			}
			return ResponseEntity.ok(tokenResponse);
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}
	}

}
