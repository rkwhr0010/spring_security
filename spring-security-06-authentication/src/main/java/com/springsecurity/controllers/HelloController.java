package com.springsecurity.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
    
    @GetMapping("/securityContext")
    public SecurityContext securityContext() {
    	System.out.println("=========== 보안 컨텍스트 =============");
    	System.out.println(SecurityContextHolder.getContext());
    	return SecurityContextHolder.getContext();
    }
    @Async
    @GetMapping("/asyncSecurityContext")
    public void asyncSecurityContext() {
    	System.out.println("=========== 비동기 보안 컨텍스트 =============");
    	System.out.println(SecurityContextHolder.getContext());
    }
    
    @GetMapping("/authentication")
    public Authentication authentication(Authentication authentication) {
    	return authentication;
    }
    
}
