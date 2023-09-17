package com.springsecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.entities.Otp;

public interface OtpRepository extends JpaRepository<Otp, String>{
	
	Optional<Otp> findOtpByUsername(String username);
}
