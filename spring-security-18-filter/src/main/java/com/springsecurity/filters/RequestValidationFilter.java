package com.springsecurity.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestValidationFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var httpRequest = (HttpServletRequest)request;
		var httpResponse = (HttpServletResponse)response;
		//참고로 http 헤더는 대소문자 구분을 하지 않는다.
		String requestId = httpRequest.getHeader("ReQuEst-ID");
		//헤더 검사
		if(requestId == null || requestId.isBlank()) {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
			return;
		}
		
		chain.doFilter(request, response);
	}
}
