package com.cardosojl.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cardosojl.models.Merchant;
import com.cardosojl.repositories.MerchantRepository;

@Service
public class MerchantService {
	
	@Autowired
	MerchantRepository repository;
	Logger logger = Logger.getLogger(MerchantService.class.getName());
	
	public List<Merchant> findAll() {
		logger.info("Searching for all");
		List<Merchant> merchants = repository.findAll();
		return merchants;
	}
	
	public Merchant findOne(Integer id) {
		logger.info("Searching for a Merchant");
		Merchant merchant = repository.findById(id).orElseThrow(() -> new Error());
		return merchant;
	}
	
	public Merchant create(Merchant m) {
		logger.info("Creating a Merchant");
		return repository.save(m);
	}
	
	public Merchant updateOne(Merchant m) {
		logger.info("Updating a Merchant");
		Merchant merchant = repository.findById(m.getId()).orElseThrow(() -> new Error());
		if (m.getName() != null) merchant.setName(m.getName());
		//if (m.getPassword() != null) merchant.setPassword(m.getPassword());
		if (m.getPhone() != null) merchant.setPhone(m.getPhone());
		if (m.getEmail() != null) merchant.setEmail(m.getEmail());
		return merchant;
	}
	
	public void deleteOne(Integer id) {
		logger.info("Deleting a Merchant");
		repository.deleteById(id);
	}

}
