package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;

import com.springsecurity.repositories.CustomCsrfTokenRepository;

@Configuration
public class SecurityConfig {

	@Autowired
	private CustomCsrfTokenRepository customCsrfTokenRepository;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// set the name of the attribute the CsrfToken will be populated on
		http
			.csrf((csrf) -> csrf
				.csrfTokenRepository(customCsrfTokenRepository)
//				.csrfTokenRequestHandler(requestHandler)
			);
		
		http.authorizeHttpRequests(request -> request
				.requestMatchers("/*").permitAll() );
		
		return http.build();
	}
	
	@Bean
	CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler() {
		return new CsrfTokenRequestAttributeHandler() {
			
		};
	}
	
}
