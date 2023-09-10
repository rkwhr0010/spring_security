package com.springsecurity.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(CsrfConfigurer::disable);
		
		http.authorizeHttpRequests(request -> request
				.requestMatchers("/*").permitAll() );
		
		//기본적으로 corsConfigurationSource라는 이름의 Bean을 사용
		http.cors(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean("corsConfigurationSource")
	CorsConfigurationSource corsConfigurationSource() {
		//동일 출처는 스키마:://도메인:포트  이 세가지가 같아야 한다.
		//이떄 스키마에 기본 포트인 경우 포트는 생략해도 된다. http 80 https 443 
		var configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		
		var source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
}
