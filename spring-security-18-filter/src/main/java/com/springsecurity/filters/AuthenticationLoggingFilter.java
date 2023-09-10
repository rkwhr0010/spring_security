package com.springsecurity.filters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class AuthenticationLoggingFilter implements Filter{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		logger.info(SecurityContextHolder.getContext().toString());
		
		chain.doFilter(request, response);
	}

}
