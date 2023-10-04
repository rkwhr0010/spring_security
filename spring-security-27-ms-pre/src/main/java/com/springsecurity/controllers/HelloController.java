package com.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.services.NameService;

@RestController
public class HelloController {
	
	@Autowired
	private NameService nameService;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello, " + nameService.getName();
	}
}
