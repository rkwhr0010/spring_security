package com.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/hello")
	public String hello1() {
		return "Get Hello";
	}
	@PostMapping("/hello")
	public String hello2() {
		return "Post Hello";
	}
	
	@PostMapping("/ciao")
	public String ciao() {
		return "Post Hello";
	}
}
