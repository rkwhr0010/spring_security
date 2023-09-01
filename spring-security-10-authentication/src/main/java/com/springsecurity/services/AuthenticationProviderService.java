package com.springsecurity.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.models.CustomUserDetails;

//하나의 인증 방식을 제공하는 책임을 지닌 객체
@Service("authenticationProviderService")
public class AuthenticationProviderService implements AuthenticationProvider{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private SCryptPasswordEncoder sCryptPasswordEncoder;
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		logger.info("===================== 인증 시작 =====================");
		//user가 없는 경우는 JpaUserDetailsService에서 예외를 발생시키도록 했다.
		//따로 확인 로직은 필요없다.
		CustomUserDetails user = userDetailsService.loadUserByUsername(username);
		
		switch (user.getUser().getAlgorithm()) {
		case BCRYPT:
			return checkPassword(bCryptPasswordEncoder, password, user);
		case SCRYPT:
			return checkPassword(sCryptPasswordEncoder, password, user);
		default:
			//지원하지 않는 해시 알고리즘
			throw new BadCredentialsException("Bad credentials"); 
		}
	}
	
	private Authentication checkPassword(PasswordEncoder passwordEncoder, String rawPassword, UserDetails user) {
		logger.info("===================== 자격증명 검증 =====================");
		logger.info("===================== " 
				+ passwordEncoder.getClass().getSimpleName() + " =====================");
		if(passwordEncoder.matches(rawPassword, user.getPassword())) {
			return UsernamePasswordAuthenticationToken.authenticated(
					user.getUsername(), user.getPassword(), user.getAuthorities());
		}
		//패스워드 불일치
		throw new BadCredentialsException("Bad credentials"); 
	}
	
	//UsernamePasswordAuthenticationToken만 지원가능
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
