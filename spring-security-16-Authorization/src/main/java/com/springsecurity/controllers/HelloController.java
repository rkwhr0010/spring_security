package com.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/a")
	public String getEndpointA() {
		return "MVC - /a";
	}
	
	@GetMapping("/b")
	public String getEndpointB() {
		return "ANT - /b";
	}
	
	@GetMapping("/c")
	public String getEndpointC() {
		return "/C";
	}
}
