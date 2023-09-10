package com.springsecurity.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@PostMapping("/hello")
	public String hello() {
		return "Post Hello";
	}
	
	@PostMapping("/ciao")
	public String ciao() {
		return "Post Hello";
	}
}
