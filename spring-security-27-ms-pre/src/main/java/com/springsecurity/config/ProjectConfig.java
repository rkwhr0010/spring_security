package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//prePostEnabled 사전 사후 전역 메서드 보안 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProjectConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		var service = new InMemoryUserDetailsManager();
		
		var u1 = User.withUsername("natalie")
				.password("12345")
				.authorities("read")
				.build();
		
		var u2 = User.withUsername("emma")
				.password("12345")
				.authorities("write")
				.build();
		
		service.createUser(u1);
		service.createUser(u2);
		
		return service;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
