package com.springsecurity;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import com.springsecurity.authentication.CustomAuthenticationProvider;

@SpringBootApplication
public class AuthenticationApplication {
	
	@Bean
	ApplicationRunner applicationRunner(ApplicationContext ac) {
		return noUse -> {
		};
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class);
	}
}
