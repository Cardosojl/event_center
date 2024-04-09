package com.cardosojl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cardosojl.models.dtos.MerchantDTO;
import com.cardosojl.services.MerchantService;



@RestController
@RequestMapping("/api/merchant/V1")
public class MerchantController {
	@Autowired
	private MerchantService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MerchantDTO>> index(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "2") Integer limit) {
		Pageable pageable = PageRequest.of(page, limit);
		List<MerchantDTO> merchants = service.findAll(pageable);
		return ResponseEntity.ok(merchants);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MerchantDTO show(@PathVariable(value = "id") Long id) {
		MerchantDTO merchantDTO = service.findOne(id);
		return merchantDTO;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public MerchantDTO store(@RequestBody MerchantDTO merchant) {
		MerchantDTO merchantDTO = service.create(merchant);
		return merchantDTO;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public MerchantDTO update(@RequestBody MerchantDTO merchant) {
		MerchantDTO merchantDTO = service.updateOne(merchant);
		return merchantDTO;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.deleteOne(id);
		return ResponseEntity.noContent().build();
	}
	

}
