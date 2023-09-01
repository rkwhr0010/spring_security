package com.springsecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findUserByUsername(String u);
}
