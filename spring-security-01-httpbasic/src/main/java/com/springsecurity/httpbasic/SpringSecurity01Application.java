package com.springsecurity.httpbasic;

import java.util.Arrays;
import java.util.stream.Stream;

import org.apache.naming.factory.BeanFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = {"com.springsecurity.httpbasic"})
//@ComponentScan("com.springsecurity.httpbasic")
public class SpringSecurity01Application {
//	@Bean
//	ApplicationRunner applicationRunner(ApplicationContext ac) {
//		//FilterChainProxy  DefaultSecurityFilterChain
//		return noUse -> {
//			var filterChain = ac.getBean(DefaultSecurityFilterChain.class);
//			filterChain.getFilters().stream()
//				.forEach(System.out::println);
//			System.out.println("###########################");
//			var filterChainProxy = ac.getBean(FilterChainProxy.class);
//			filterChainProxy.getFilterChains().stream()
//				.flatMap(sfc -> sfc.getFilters().stream())
//				.forEach(System.out::println);
//			
//			
//			
//			Arrays.stream(ac.getBeanDefinitionNames())
//			.map(beanName -> ac.getBean(beanName))
//			.map(bean -> bean.getClass().toString())
//			.filter(beanName -> beanName.toLowerCase().contains("filter"))
//			.forEach(System.out::println);
//		};
//	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity01Application.class, args);
	}
}

