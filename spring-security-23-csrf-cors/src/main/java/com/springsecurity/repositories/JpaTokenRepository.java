package com.springsecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.entities.Token;

public interface JpaTokenRepository extends JpaRepository<Token, Integer> {
	Optional<Token> findByIdentifier(String identifier);
}
