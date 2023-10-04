package com.springsecurity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import com.springsecurity.model.Product;

@Service
public class ProductService {

	//반드시 컬렉션이나 배열이 반환 값이여야 한다.
//	@PostFilter("filterObject.owner == authentication.name")
	@PostFilter("authentication.name == filterObject.owner")
	public List<Product> findProducts() {
		List<Product> products = new ArrayList<>();
		
		products.add(new Product("beer", "nikolai"));
		products.add(new Product("candy", "nikolai"));
		products.add(new Product("chocolate", "julien"));
		
		return products;
	}
}
