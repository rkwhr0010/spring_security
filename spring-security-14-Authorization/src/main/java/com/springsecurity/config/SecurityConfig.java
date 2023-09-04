package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {
	
	@Autowired 
	private ApplicationContext ac;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		var manager = inMemoryUserDetailsManager();
		
		var user1 = User.withUsername("scott")
			.password("tiger")
			.roles(Role.ADMIN.name(), Role.MANAGER.name())
			.build();
		
		var user2 = User.withUsername("john")
			.password("12345")
			.roles(Role.ADMIN.name())
			.build();
		
		var user3 = User.withUsername("jane")
			.password("12345")
			.roles(Role.MANAGER.name())
			.build();
		
		manager.createUser(user1);
		manager.createUser(user2);
		manager.createUser(user3);
		
		//저장소에서 유저상세를 찾을 UserDetailsService 등록
		http.userDetailsService(manager);
		
		http.httpBasic(Customizer.withDefaults());
		
		authorizeHttpRequests(http);
		
		return http.build();
	}

	void authorizeHttpRequests(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> {
			authorize.requestMatchers(
					mvcMatchers("/hello")).hasAnyRole(Role.ADMIN.name());
			
			authorize.requestMatchers(
					mvcMatchers("/ciao")).hasAnyRole(Role.MANAGER.name());
			
			authorize.anyRequest().permitAll();
		});
	}
	
	private MvcRequestMatcher mvcMatchers(String pattern) {
		return new MvcRequestMatcher(ac.getBean(HandlerMappingIntrospector.class), pattern);
	}
	
	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager();
	}
	
	@Bean 
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
