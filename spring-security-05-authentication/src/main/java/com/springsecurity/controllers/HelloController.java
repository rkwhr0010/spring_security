package com.springsecurity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
    
    @GetMapping("/securityContext")
    public SecurityContext securityContext() {
    	return SecurityContextHolder.getContext();
    }
    
    @GetMapping("/authentication")
    public Authentication authentication(Authentication authentication) {
    	return authentication;
    }
    
}
