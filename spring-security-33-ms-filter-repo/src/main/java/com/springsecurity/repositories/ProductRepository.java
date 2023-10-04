package com.springsecurity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

import com.springsecurity.entityies.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
//	@PostFilter("filterObject.owner == authentication.principal.username")
	@PostFilter("filterObject.owner == authentication.name")
	List<Product> findProductByNameContains(String text);
}
