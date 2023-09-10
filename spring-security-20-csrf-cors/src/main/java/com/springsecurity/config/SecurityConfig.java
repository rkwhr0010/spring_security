package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

import jakarta.servlet.Filter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	@Qualifier("csrfTokenLogger")
	private Filter filter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.addFilterAfter(filter, CsrfFilter.class);
		
		http.csrf(csrf -> {
			csrf.csrfTokenRepository(new  CookieCsrfTokenRepository());
		});
		
		http.authorizeHttpRequests(request -> { request 
				.anyRequest().permitAll();
		});
		
		return http.build();
	}
}
