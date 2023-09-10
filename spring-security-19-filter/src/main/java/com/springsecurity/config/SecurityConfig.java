package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.Filter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	@Qualifier("staticKeyAuthenticationFilter")
	private Filter filter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.httpBasic((basic) -> {});
		//해당 필터 같은 우선순위로 끼워넣기
		http.addFilterAt(filter, BasicAuthenticationFilter.class);
		
		http.authorizeHttpRequests(request -> { request 
				.anyRequest().permitAll();
		});
		
		return http.build();
	}
	
}
