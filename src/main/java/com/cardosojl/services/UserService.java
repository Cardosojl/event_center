package com.cardosojl.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.cardosojl.models.UserInterface;
import com.cardosojl.models.dtos.MerchantDTO;
import com.cardosojl.models.dtos.OrganizerDTO;
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
	
	public List<UserDTO<List<String>>> findAll(Pageable pageable) {
		logger.info("Searching for all");
		Page<User> userPages = repository.findAll(pageable);
		Page<UserDTO<List<String>>> userDTO = userPages.map(u -> new UserDTO<List<String>> (u));		
		return userDTO.getContent();	
	}
	
	public UserDTO<List<String>> findOne(Long id) {
		logger.info("Searching one User");
		UserDTO<List<String>> userDTO = new UserDTO<List<String>>(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")));
		return userDTO;
	}
	
	public OrganizerDTO findOneOrganizer(Long id) {
		logger.info("Searching one Organizer");
		OrganizerDTO organizer = new OrganizerDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")));
		if (!organizer.isAnAcceptableRole()) {
			throw new ResourceNotFoundException("No organizer found for this ID");
		}
		return organizer;		
	}
	
	public MerchantDTO findOneMerchant(Long id) {
		logger.info("Searching one Merchant");
		MerchantDTO merchant = new MerchantDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")));
		if (!merchant.isAnAcceptableRole()) {
			throw new ResourceNotFoundException("No merchant found for this ID");
		}
		return merchant;		
	}
	
	public UserDTO<List<String>> create(UserDTO<Integer> u) {
		logger.info("Creating an User");
		String password = generateCryptography(u.getPassword());
		Permission permission = permissionRepository.findById((long) u.getPermissions()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		User user = new User(u.getEmail(),
				u.getName(),
				u.isAccontNonExpired(),
				u.isAccontNonLocked(),
				u.isCredentialsNonExpired(),
				u.isEnabled(),
				password,
				List.of(permission));
		return new UserDTO<List<String>> (repository.save(user));		
	}
	
	public UserDTO<String> updateOne(UserDTO<Integer> u) {
		logger.info("Updating an User");
		User user = repository.findById(u.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		if (u.getEmail() != null) user.setEmail(u.getEmail());
		if (u.getName() != null) user.setName(u.getName());
		if (u.getPassword() != null) user.setPassword(generateCryptography(u.getPassword()));
		if (u.isAccontNonExpired() != null) user.setAccountNonExpired(u.isAccontNonExpired());
		if (u.isAccontNonLocked() != null) user.setAccountNonLocked(u.isAccontNonLocked());
		if (u.isCredentialsNonExpired() != null) user.setCredentialsNonExpired(u.isCredentialsNonExpired());
		if (u.isEnabled() != null) user.setEnabled(u.isEnabled());
		System.out.println(u.getPermissions());
		if (u.getPermissions() != null) List.of(permissionRepository.findById((long) u.getPermissions()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")));
		return new UserDTO<String>(repository.save(user));
	}
	
	public MerchantDTO updateOneMerchant(UserInterface m) {
		logger.info("Updating a Merchant");
		User user = repository.findById(m.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		MerchantDTO merchant = new MerchantDTO(user);
		if (!merchant.isAnAcceptableRole()) {
			throw new ResourceNotFoundException("No merchant found for this ID");
		}
		if (m.getEmail() != null) user.setEmail(m.getEmail());
		if (m.getName() != null) user.setName(m.getName());
		if (m.getPassword() != null) user.setPassword(generateCryptography(m.getPassword()));		
		return new MerchantDTO(repository.save(user));
	}
	
	public OrganizerDTO updateOneOrganizer(UserInterface m) {
		logger.info("Updating a Merchant");
		User user = repository.findById(m.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		OrganizerDTO organizer = new OrganizerDTO(user);
		if (!organizer.isAnAcceptableRole()) {
			throw new ResourceNotFoundException("No organizer found for this ID");
		}
		if (m.getEmail() != null) user.setEmail(m.getEmail());
		if (m.getName() != null) user.setName(m.getName());
		if (m.getPassword() != null) user.setPassword(generateCryptography(m.getPassword()));		
		return new OrganizerDTO(repository.save(user));
	}
	
	public void deleteOne(Long id) {
		logger.info("Deleting an User");
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.deleteById(id);
	}
	
	private String generateCryptography(String pswd) {
		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);		
		String password = passwordEncoder.encode(pswd);
		if (password.contains("{pbkdf2}")) password = password.replace("{pbkdf2}", "");
		return password;
	}	
	

}
