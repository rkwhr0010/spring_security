package com.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableAsync
public class SecurityConfig {
	
    @Bean
    InitializingBean initializingBean() {
        return () -> SecurityContextHolder.setStrategyName(
        		SecurityContextHolder.MODE_THREADLOCAL);
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
    	return new JdbcUserDetailsManager(dataSource);
    }
}
