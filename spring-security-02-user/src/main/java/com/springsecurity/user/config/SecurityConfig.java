package com.springsecurity.user.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springsecurity.user.model.User;
import com.springsecurity.user.service.InMemoryUserDetailsService;

@Configuration
public class SecurityConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		User user = new User("scott", "tiger", "read");
		
		return new InMemoryUserDetailsService(List.of(user));
	}
	//UserDetailsService과 PasswordEncoder 같이 설정해야 한다.
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
