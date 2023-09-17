package com.springsecurity.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.springsecurity.authentication.OtpAuthentication;
import com.springsecurity.proxy.AuthenticationServerProxy;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private AuthenticationServerProxy proxy;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String code = auth.getCredentials().toString();
		
		boolean result = proxy.sendOTP(username, code);
		
		if(result) {
			return new OtpAuthentication(username, code);
		} else {
			throw new BadCredentialsException("Bad credentials!!!");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return OtpAuthentication.class.isAssignableFrom(clazz);
	}
}
