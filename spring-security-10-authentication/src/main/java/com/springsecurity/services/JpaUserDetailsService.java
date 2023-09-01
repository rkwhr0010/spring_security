package com.springsecurity.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.models.CustomUserDetails;
import com.springsecurity.repositories.UserRepository;

//조회만 필요해서 UserDetailsService, CUD 필요하면 UserDetailsManager 구현
@Service
public class JpaUserDetailsService implements UserDetailsService{
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserRepository userRepository;
	
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("===================== 유저 탐색 =====================");
		return new CustomUserDetails(
				userRepository.findUserByUsername(username)
				//사용자가 없으면, 예외를 던진다.
				.orElseThrow(() -> new UsernameNotFoundException("Authentication Failed !!!"))
				);
	}

}
