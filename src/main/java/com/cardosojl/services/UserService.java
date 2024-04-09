package com.cardosojl.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cardosojl.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository repository;
	private Logger logger = Logger.getLogger(UserService.class.getName());
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		logger.info("Searching one user by email " + email + "." );
		var user = repository.findByUsername(email);
		if (user != null) {
			return user;
			
		} else {
			throw new UsernameNotFoundException("Email " + email + " not found");
		}
	}
	
	public UserDT
	
	

}
