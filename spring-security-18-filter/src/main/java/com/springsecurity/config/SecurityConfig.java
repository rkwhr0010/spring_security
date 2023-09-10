package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.springsecurity.filters.AuthenticationLoggingFilter;
import com.springsecurity.filters.RequestValidationFilter;

@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		configUserDetailsManager();
		//기본 인증 사용
		http.httpBasic((basic) -> {});
		//해당 필터 앞에 끼워넣기
		http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class);
		http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);
		
		http.authorizeHttpRequests(request -> { request 
				.anyRequest().authenticated();
		});
		
		return http.build();
	}

	void configUserDetailsManager() {
		var manager = inMemoryUserDetailsManager();
		
		var user1 = User.withUsername("scott")
				.password("tiger")
				.roles("ADMIN", "MANAGER")
				.build();
		var user2 = User.withUsername("john")
				.password("12345")
				.roles("ADMIN")
				.build();
		var user3 = User.withUsername("jane")
				.password("12345")
				.roles("MANAGER")
				.build();
		
		manager.createUser(user1);
		manager.createUser(user2);
		manager.createUser(user3);
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
