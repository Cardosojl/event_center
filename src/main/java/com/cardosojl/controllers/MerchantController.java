package com.cardosojl.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardosojl.models.dtos.MerchantDTO;
import com.cardosojl.services.UserService;



@RestController
@RequestMapping("/api/merchant/V1")
public class MerchantController {
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)              
	public MerchantDTO show(@PathVariable(value = "id") Long id) {
		MerchantDTO merchant = service.findOneMerchant(id);
		return merchant;
	}
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)     
	public MerchantDTO update(@RequestBody MerchantDTO merchant) {
		MerchantDTO merchantDTO = service.updateOneMerchant(merchant);
		return merchantDTO;
	}
	

}
