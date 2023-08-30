package com.springsecurity.controllers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
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
    
    @GetMapping("/securityContext1")
    public SecurityContext securityContext1() throws Exception{
    	Callable<SecurityContext> task = () -> {
    		//비동기 작업, 새 스레드에서 읽기
    		SecurityContext context = SecurityContextHolder.getContext();
    		return context;
    	};
    	ExecutorService e = Executors.newCachedThreadPool();
    	try {
    		return e.submit(new DelegatingSecurityContextCallable<>(task)).get();
    	} finally {
			e.shutdown();
		}
    }
    
    @GetMapping("/securityContext2")
    public SecurityContext securityContext2() throws Exception{
    	Callable<SecurityContext> task = () -> {
    		//비동기 작업, 새 스레드에서 읽기
    		SecurityContext context = SecurityContextHolder.getContext();
    		return context;
    	};
    	var e = new DelegatingSecurityContextExecutorService(Executors.newCachedThreadPool());
    	
    	try {
    		return e.submit(task).get();
    	} finally {
    		e.shutdown();
    	}
    }
    
    
    @GetMapping("/authentication")
    public Authentication authentication(Authentication authentication) {
    	return authentication;
    }
    
}
