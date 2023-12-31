package com.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.springsecurity.security.DocumentsPermissionEvaluator;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProjectConfig extends GlobalMethodSecurityConfiguration {
	
	@Autowired
	private DocumentsPermissionEvaluator evaluator;
	
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		//맞춤형 사용 권한 형가기 구성을 위한 기본 보안식 처리기 설정
		var expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setPermissionEvaluator(evaluator);
		
		return expressionHandler;
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		var service = new InMemoryUserDetailsManager();
		
		var u1 = User.withUsername("natalie")
				.password("12345")
				.roles("admin")
				.build();
		
		var u2 = User.withUsername("emma")
				.password("12345")
				.roles("manager")
				.build();
		
		service.createUser(u1);
		service.createUser(u2);
		
		return service;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}