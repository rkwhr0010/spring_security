package com.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springsecurity.services.ProductService;

//MVC 컨트롤러
@Controller
public class MainPageController {
	@Autowired
	private ProductService productService;

	@GetMapping("/main")
	public String main(Authentication auth, Model model) {
		model.addAttribute("username", auth.getName());
		model.addAttribute("authorities", auth.getAuthorities());
		model.addAttribute("products", productService.findAll());
		
		return "main.html";
	}
	
}
