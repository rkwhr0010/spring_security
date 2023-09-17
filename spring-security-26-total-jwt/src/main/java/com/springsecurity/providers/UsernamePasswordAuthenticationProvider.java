package com.springsecurity.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.springsecurity.authentication.UsernamePasswordAuthentication;
import com.springsecurity.proxy.AuthenticationServerProxy;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private AuthenticationServerProxy proxy;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		
		proxy.sendAuth(username, password);
		//이중 인증이기에 ID/PW가 인증 성공해도 아직 인증된 사용자는 아니다.
		return new UsernamePasswordAuthentication(username, password);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UsernamePasswordAuthentication.class.isAssignableFrom(clazz);
	}
}
