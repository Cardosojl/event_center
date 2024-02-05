package com.cardosojl.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardosojl.models.EventType;



@RestController
@RequestMapping("/api/merchants/V1")
public class MerchantController {
	@GetMapping(value = "")
	public EventType index() {
		EventType ev = new EventType();
		ev.setId(1);
		ev.setType("música");
		return ev;		
	}
	

}
