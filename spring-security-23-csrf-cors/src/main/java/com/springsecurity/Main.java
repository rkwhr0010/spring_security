package com.springsecurity;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class Main {
//	@Bean
	ApplicationRunner applicationRunner(BeanFactory factory) {
		return noUse -> {
			FilterChainProxy filterChainProxy = factory.getBean(FilterChainProxy.class);
			SecurityFilterChain securityFilterChain = filterChainProxy.getFilterChains().get(0);
			
			securityFilterChain.getFilters().stream()
				.map(filter -> filter.getClass().getSimpleName())
				.forEach(System.out::println);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
