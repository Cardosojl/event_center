package com.cardosojl.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.stereotype.Service;

import com.cardosojl.exceptions.exceptions.ResourceNotFoundException;
import com.cardosojl.models.Permission;
import com.cardosojl.models.User;
import com.cardosojl.models.dtos.UserDTO;
import com.cardosojl.repositories.PermissionRepository;
import com.cardosojl.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository repository;
	@Autowired
	PermissionRepository permissionRepository;
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
	
	public UserDTO create(UserDTO<Integer> u) {
		logger.info("Creating an User");
		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);		
		String password = passwordEncoder.encode(u.getPassword());
		Permission permission = permissionRepository.findById((long) u.getPermissions()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		if (password.contains("{pbkdf2}")) password = password.replace("{pbkdf2}", "");
		System.out.println(password);
		User user = new User(u.getEmail(),
				u.getName(),
				u.getAccontNonExpired(),
				u.getAccontNonLocked(),
				u.getCredentialsNonExpired(),
				u.getEnabled(),
				password,
				List.of(permission));
		return new UserDTO(repository.save(user));		
	}
	
	

}
