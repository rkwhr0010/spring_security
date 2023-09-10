package com.springsecurity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/")
	public String main() {
		return "main.html";
	}
	
	@PostMapping("/test")
	@ResponseBody
//	@CrossOrigin("http://localhost:8080")
//	@CrossOrigin(origins = {"http://localhost:8080"}, methods = {RequestMethod.POST})
	public String test() {
		logger.info("Test method called");
		return "Hello";
	}
}
