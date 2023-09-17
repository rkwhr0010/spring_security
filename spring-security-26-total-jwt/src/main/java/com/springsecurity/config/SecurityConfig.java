package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.springsecurity.filters.InitialAuthenticationFilter;
import com.springsecurity.filters.JwtAuthenticationFilter;
import com.springsecurity.providers.OtpAuthenticationProvider;
import com.springsecurity.providers.UsernamePasswordAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private InitialAuthenticationFilter initialAuthenticationFilter;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private OtpAuthenticationProvider otpAuthenticationProvider;
	
	@Autowired
	private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(otpAuthenticationProvider)
			.authenticationProvider(usernamePasswordAuthenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//모든 요청을 받기 위한 설정
		http.csrf(CsrfConfigurer::disable);
		
		http.addFilterAt(initialAuthenticationFilter, 
				BasicAuthenticationFilter.class);
		http.addFilterAfter(jwtAuthenticationFilter, 
				BasicAuthenticationFilter.class);
		
		http.authorizeRequests(request -> { request
			.anyRequest().authenticated();
		});
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
