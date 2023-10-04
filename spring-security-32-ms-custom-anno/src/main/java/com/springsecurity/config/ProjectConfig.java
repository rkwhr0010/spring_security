package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true, // @PreAuthorize, @PostAuthorize 활성화
		jsr250Enabled = true,  // @RolesAllowred 활성화
		securedEnabled = true  // @Secured 활성화
		)
public class ProjectConfig extends GlobalMethodSecurityConfiguration {
	
	@Bean
	UserDetailsService userDetailsService() {
		var service = new InMemoryUserDetailsManager();
		
		var u1 = User.withUsername("natalie")
				.password("12345")
				.roles("ADMIN")
				.build();
		
		var u2 = User.withUsername("emma")
				.password("12345")
				.roles("MANAGER")
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