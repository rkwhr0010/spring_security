package com.springsecurity.controllers;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/hello")
	public String hello(CsrfToken csrfToken) {
		return "GET - Hello!!!  " + csrfToken.getToken();
	}
	
	@PostMapping("/hello")
	public String hello2(CsrfToken csrfToken) {
		return "POST - Hello!!!   " + csrfToken.getToken();
	}
}
