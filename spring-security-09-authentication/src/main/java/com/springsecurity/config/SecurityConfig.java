package com.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.springsecurity.authentication.CustomAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain config(HttpSecurity http) throws Exception {
		http.httpBasic(c -> {
			c.realmName("ADMIN");
			c.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
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
