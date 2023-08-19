package com.springsecurity.httpbasic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
	@GetMapping("/resource")
	String resource() {
		return "resource";
	}
}
