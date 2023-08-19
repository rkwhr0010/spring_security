package com.springsecurity.httpbasic.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {
	
	//책임_저장소에서 해당 유저가 존재하는지 확인 및 유저 권한 조회
	@Bean
	UserDetailsService userDetailsService() {
		var userDetailsService = new InMemoryUserDetailsManager();	
		userDetailsService.createUser(
				User.withUsername("scott")
					.password("tiger")
					.passwordEncoder(pw -> passwordEncoder().encode(pw))
					.authorities(List.of())
					.build()
				);
		
		return userDetailsService;
	}
	//책임_Password를 해싱, 입력된 Password를 해싱된 password와 비교하여 일치하는지 확인
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().authenticated() //모든 경로는 인증돼야 함
			);
		//인증 활성화
		http.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
}
