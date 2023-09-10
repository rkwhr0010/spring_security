package com.springsecurity.filters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CsrfTokenLogger extends OncePerRequestFilter{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		Object o = request.getAttribute("_csrf"); //_csrf 로 설정된다.
		CsrfToken token = (CsrfToken) o;
//		logger.info("CSRF Token = " + token.getToken());
		
//		String sessionToken = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
//		System.out.println(((CsrfToken)request.getSession().getAttribute(sessionToken)).getToken() );
		
		filterChain.doFilter(request, response);
	}
}
