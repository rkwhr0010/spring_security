package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	//유저가 직접 작성한 코드는 보통 편리하게 어노테이션으로 스캔을 통해 빈으로 등록을 한다.
	//반면에 라이브러리로 제공받은 코드는 어노테이션 붙일 수가 없기에 따로 빈으로 생성해야 한다.
	@Autowired
	@Qualifier("authenticationProviderService")
	@Lazy //순환 참조 문제로 레이지
	AuthenticationProvider authenticationProvider;
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	SCryptPasswordEncoder sCryptPasswordEncoder() {
		return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(form -> {
			form.usernameParameter("username") //원래 기본값이다.(생략가능)
				.passwordParameter("password") //기본값
				.defaultSuccessUrl("/main", true);
		});
		//커스텀 구현 사용하도록 등록
		http.authenticationProvider(authenticationProvider);
		
		http.authorizeHttpRequests(request -> {
			request.anyRequest().authenticated();//모든 요청은 인증이 되야 한다.
		});
		
		return http.build();
	}
	
	
}
