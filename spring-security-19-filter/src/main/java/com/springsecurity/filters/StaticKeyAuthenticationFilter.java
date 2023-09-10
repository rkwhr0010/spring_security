package com.springsecurity.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("staticKeyAuthenticationFilter")
public class StaticKeyAuthenticationFilter extends OncePerRequestFilter{
	
	@Value("${authorization.key}")
	private String authorizationKey;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authentication = request.getHeader("Authorization");
		
		System.out.println(authentication + "   " + authorizationKey);
		
		if(authentication.equals(authentication)) {
			filterChain.doFilter(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		
	}
}

