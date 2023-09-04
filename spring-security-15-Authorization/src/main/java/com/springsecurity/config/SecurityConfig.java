package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher.Builder;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {
	
	@Autowired 
	private ApplicationContext ac;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		configUserDetailsService(http);
		//GET요청을 제외한 실습을 위해 기능 정지
		http.csrf(CsrfConfigurer::disable);
		
		http.httpBasic(Customizer.withDefaults());
		
		authorizeHttpRequests(http);
		
		return http.build();
	}

	void configUserDetailsService(HttpSecurity http) throws Exception {
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
		
		http.userDetailsService(manager);
	}

	void authorizeHttpRequests(HttpSecurity http) throws Exception {
		Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(
				ac.getBean(HandlerMappingIntrospector.class));
		
		http.authorizeHttpRequests(authorize -> {
			authorize.requestMatchers(
				mvcMatcherBuilder.pattern(HttpMethod.GET, "/a")
				).authenticated();
			
			authorize.requestMatchers(
				mvcMatcherBuilder.pattern(HttpMethod.POST,"/a")
				).permitAll();
			
			authorize.requestMatchers(
				/*mvcMatcherBuilder.pattern*/("/product/{code:^[0-9]*$}")
				).permitAll();
			
			authorize.anyRequest()
				.denyAll();
		});
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
