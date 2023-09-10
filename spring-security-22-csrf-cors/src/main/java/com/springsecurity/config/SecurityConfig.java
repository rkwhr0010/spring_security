package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher.Builder;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, 
			HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
		
		http.csrf(csrf -> {
			csrf.ignoringRequestMatchers(
					"/ciao"	
//					createMvcRequestMatcher(handlerMappingIntrospector).pattern("/ciao")
//					AntPathRequestMatcher.antMatcher("/ciao")
//					RegexRequestMatcher.regexMatcher("\\/ciao")
					);
		});
		
		http.authorizeHttpRequests(
				request -> {
					request.anyRequest().permitAll();
				});

		return http.build();
	}

	Builder createMvcRequestMatcher(
			HandlerMappingIntrospector handlerMappingIntrospector) {
		return new MvcRequestMatcher.Builder(handlerMappingIntrospector);
	}
}
