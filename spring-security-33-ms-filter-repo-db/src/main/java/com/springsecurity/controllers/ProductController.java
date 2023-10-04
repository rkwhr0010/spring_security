package com.springsecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.entityies.Product;
import com.springsecurity.repositories.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products/{text}")
	public List<Product> findProductsContaining(@PathVariable String text, Authentication auth) {
		System.out.println("\n\n\n\n");
		System.out.println(auth);
		
		return productRepository.findProductByNameContains(text);
	}
}
