package com.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Say Hello!!!";
	}
	
	@GetMapping("/ciao")
	public String ciao() {
		return "Ciao!!!";
	}
	
	@GetMapping("/hola")
	public String hola() {
		return "hola!!!";
	}
}
