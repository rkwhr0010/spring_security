package com.springsecurity.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class OtpAuthentication extends UsernamePasswordAuthenticationToken {
	//단순히 인증 객체를 생성할 경우 사용, 아직 인증이 되지 않은 객체
	public OtpAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
	//인증 완료된 객체를 생성할 때 사용
	public OtpAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
