package com.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	@GetMapping("/email/{email}")
	public String eamil(@PathVariable String email) {
		return email;
	}
}
