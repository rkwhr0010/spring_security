package com.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{}
