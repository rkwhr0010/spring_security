package com.springsecurity.controllers;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@GetMapping("/main")
	public String hello() {
		return "main";
	}
	
	@GetMapping("/csrf")
	@ResponseBody
	public String csrf(CsrfToken token) {
		return token.getToken();
	}
}
