package com.cardosojl.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cardosojl.exceptions.exceptions.ResourceNotFoundException;
import com.cardosojl.models.Merchant;
import com.cardosojl.models.dtos.MerchantDTO;
import com.cardosojl.repositories.MerchantRepository;

@Service
public class MerchantService {
	
	@Autowired
	MerchantRepository repository;
	Logger logger = Logger.getLogger(MerchantService.class.getName());
	
	public List<MerchantDTO> findAll(Pageable pageable) {
		logger.info("Searching for all");
		Page<Merchant> merchantPages = repository.findAll(pageable);
		Page<MerchantDTO> merchantsDTO = merchantPages.map(m -> new MerchantDTO(m));
		return merchantsDTO.getContent();
	}
	
	public MerchantDTO findOne(Long id) {
		logger.info("Searching for a Merchant");
		Merchant merchant = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return new MerchantDTO(merchant);
	}
	
	public MerchantDTO create(MerchantDTO m) {
		logger.info("Creating a Merchant");
		Merchant merchant = new Merchant(m.getName(), m.getPhone(), m.getEmail(), m.getPassword());
		return new MerchantDTO(repository.save(merchant));
	}
	
	public MerchantDTO updateOne(MerchantDTO m) {
		logger.info("Updating a Merchant");
		Merchant merchant = repository.findById(m.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		if (m.getName() != null) merchant.setName(m.getName());
		//if (m.getPassword() != null) merchant.setPassword(m.getPassword());
		if (m.getPhone() != null) merchant.setPhone(m.getPhone());
		if (m.getEmail() != null) merchant.setEmail(m.getEmail());
		return new MerchantDTO(merchant);
	}
	
	public void deleteOne(Long id) {
		logger.info("Deleting a Merchant");
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.deleteById(id);
	}

}
