package com.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/a")
	public String getEndpointA() {
		return "Get - /a";
	}
	
	@PostMapping("/a")
	public String postEndpointA() {
		return "Post - /a";
	}
	
	@GetMapping("/a/b")
	public String getEndpointB() {
		return "Get - /a/b";
	}
	
	@GetMapping("/a/b/c")
	public String getEndpointC() {
		return "Get - /a/b/c";
	}
	
}
