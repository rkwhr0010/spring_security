package com.springsecurity.config;

import java.time.LocalTime;
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
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		var manager = inMemoryUserDetailsManager();
		
		var user1 = User.withUsername("scott")
			.password("tiger")
//			.authorities("ROLE_ADMIN")
			.roles(ROLE.ADMIN.name(), ROLE.MANAGER.name())
			.build();
		
		var user2 = User.withUsername("john")
			.password("12345")
//			.authorities("ROLE_ADMIN")
			.roles(ROLE.ADMIN.name())
			.build();
		
		var user3 = User.withUsername("jane")
			.password("12345")
//			.authorities("ROLE_MANAGER")
			.roles(ROLE.MANAGER.name())
			.build();
		
		manager.createUser(user1);
		manager.createUser(user2);
		manager.createUser(user3);
		
		//저장소에서 유저상세를 찾을 UserDetailsService 등록
		http.userDetailsService(manager);
		
		http.httpBasic(Customizer.withDefaults());
		
		http.authorizeHttpRequests(request -> {
//			hasRole(request);
//			hasAnyRole(request);
			access1(request);
//			access2(request);
		
		});
		
		return http.build();
	}

	void hasRole(AuthorizeHttpRequestsConfigurer<HttpSecurity>
		.AuthorizationManagerRequestMatcherRegistry request) {
		//하나의 권한만
		request.anyRequest()
			.hasRole(ROLE.ADMIN.name());
	}
	void hasAnyRole(AuthorizeHttpRequestsConfigurer<HttpSecurity>
		.AuthorizationManagerRequestMatcherRegistry request) {
		//하나 이상의 권한 (개인적으로 이걸 추천)
		request.anyRequest()
		.hasAnyRole(ROLE.ADMIN.name(), ROLE.MANAGER.name());
	}
	
	void access1(AuthorizeHttpRequestsConfigurer<HttpSecurity>
		.AuthorizationManagerRequestMatcherRegistry request) {
		request.anyRequest()
			.access((authentication, obj) -> {
				
				//업무 시간
				var now = LocalTime.now();
				var amNine = now.isAfter(LocalTime.of(9, 0));
				var pmSix = now.isBefore(LocalTime.of(18, 0));
				
				if(!(amNine && pmSix)) {
					return new AuthorizationDecision(false);
				}
				
				//허용할 권한 목록
				List<String> permitAuthority = 
						List.of("ROLE_" + ROLE.ADMIN.name(), 
								"ROLE_" + ROLE.MANAGER.name());
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
