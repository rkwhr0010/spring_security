package com.springsecurity.httpbasic.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) 
			throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		logger.info("username = " + username + ", password = " + password);
		
//		simpleAuthentication(username, password);
		
		UserDetails user = userDetailsService.loadUserByUsername(username);
		if(user == null || !encoder.matches(password, user.getPassword())) {
			throw new UsernameNotFoundException("인증 실패");
		}
		return new UsernamePasswordAuthenticationToken(username, password, List.of());
	}

	private void simpleAuthentication(String username, String password) {
		if(!"scott".equals(username) || ! "tiger".equals(password)) {
			throw new UsernameNotFoundException("인증 실패");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication);
	}
}