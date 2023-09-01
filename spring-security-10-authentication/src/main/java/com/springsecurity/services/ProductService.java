package com.springsecurity.services;

import java.util.List;

import com.springsecurity.entities.Product;

public interface ProductService {
	List<Product> findAll();
}
