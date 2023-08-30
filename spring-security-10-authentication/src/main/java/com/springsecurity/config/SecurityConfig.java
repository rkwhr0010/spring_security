package com.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain config(HttpSecurity http
			, AuthenticationFailureHandler failureHandler
			, AuthenticationSuccessHandler successHandler
			, AuthenticationEntryPoint entryPoint) throws Exception {
		http.formLogin( form -> {
			form.defaultSuccessUrl("/home", true)
				.failureHandler(failureHandler)
				.successHandler(successHandler);
		});
		
		http.httpBasic(c -> {
			c.authenticationEntryPoint(entryPoint);
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
    UserDetailsService userDetailsService(DataSource dataSource) {
    	return new JdbcUserDetailsManager(dataSource);
    }
}
