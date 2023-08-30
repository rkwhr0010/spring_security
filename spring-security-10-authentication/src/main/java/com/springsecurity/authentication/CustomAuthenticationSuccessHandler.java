package com.springsecurity.authentication;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		var auth = authentication.getAuthorities()
			.stream()
			.filter(a -> a.getAuthority().equals("write"))
			.findFirst();
		System.out.println(auth);
		if(auth.isEmpty()) {
			//권한 없음
			response.sendRedirect("/error");
		} else {
			//권한 존재
			response.sendRedirect("/home");
		}
	}
}
