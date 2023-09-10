package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		InMemoryUserDetailsManager userDetailsManager = userDetailsManager();

		UserDetails user = User.withUsername("mary").password("12345").authorities("READ").build();

		userDetailsManager.createUser(user);
		http.formLogin(form -> {
			form.defaultSuccessUrl("/main", true);
		});
		
		http.authorizeHttpRequests(request -> {
			request.anyRequest().authenticated();
		});

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	InMemoryUserDetailsManager userDetailsManager() {
		return new InMemoryUserDetailsManager();
	}

}
