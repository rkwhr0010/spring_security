package com.springsecurity.config;

import java.util.List;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

@Configuration
public class SecurityConfig {
	
	@SuppressWarnings("deprecation")
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		var manager = inMemoryUserDetailsManager();
		
		var user1 = User.withUsername("scott")
			.password("tiger")
			.authorities("READ", "WRITE")
			.build();
		
		var user2 = User.withUsername("john")
			.password("12345")
			.authorities("READ")
			.build();
		
		var user3 = User.withUsername("jane")
				.password("12345")
				.authorities("WRITE")
				.build();
		
		manager.createUser(user1);
		manager.createUser(user2);
		manager.createUser(user3);
		
		//저장소에서 유저상세를 찾을 UserDetailsService 등록
		http.userDetailsService(manager);
		
		http.httpBasic(Customizer.withDefaults());
		
		http.authorizeHttpRequests(request -> {
//			hasAuthority(request);
//			hasAnyAuthority(request);
//			access1(request);
			access2(request);
		
		});
		
		return http.build();
	}

	void hasAuthority(AuthorizeHttpRequestsConfigurer<HttpSecurity>
		.AuthorizationManagerRequestMatcherRegistry request) {
		//하나의 권한만
		request.anyRequest()
			.hasAuthority("READ");
	}
	void hasAnyAuthority(AuthorizeHttpRequestsConfigurer<HttpSecurity>
		.AuthorizationManagerRequestMatcherRegistry request) {
		//하나 이상의 권한 (개인적으로 이걸 추천)
		request.anyRequest()
		.hasAnyAuthority("WRITE", "READ");
	}
	
	void access1(AuthorizeHttpRequestsConfigurer<HttpSecurity>
		.AuthorizationManagerRequestMatcherRegistry request) {
		request.anyRequest()
			.access((authentication, obj) -> {
				//허용할 권한 목록
				List<String> permitAuthority = List.of("WRITE");
				//인증 객체로 검사
				var auth = authentication.get();
				
				var findFirst = auth.getAuthorities()
					.stream()
					.map(a -> a.getAuthority())
					.filter(a -> permitAuthority.contains(a))
					.findFirst();
			
				return new AuthorizationDecision(findFirst.isPresent());
			});
	}
	
	void access2(AuthorizeHttpRequestsConfigurer<HttpSecurity>
		.AuthorizationManagerRequestMatcherRegistry request) {
		request.anyRequest()
			.access((authentication, obj) -> {
				var rac = (RequestAuthorizationContext)obj;
				String header = rac.getRequest().getHeader("trust");
				
				return new AuthorizationDecision(Objects.nonNull(header));
			
			});
	}
	
	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager();
	}
	
	@Bean 
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
