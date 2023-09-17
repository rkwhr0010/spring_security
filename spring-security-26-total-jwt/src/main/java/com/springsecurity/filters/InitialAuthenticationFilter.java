package com.springsecurity.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springsecurity.authentication.OtpAuthentication;
import com.springsecurity.authentication.UsernamePasswordAuthentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter{

	//기본 AuthenticationManager를 빈으로 등록해줘야 한다.
	@Autowired
	private AuthenticationManager manager;
	
	@Value("${jwt.signing.key}")
	private String signingKey;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//헤더에서 정보를 가져온다.
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		String code = request.getHeader("code");
		
		//code가 null인 경우는 첫 인증인 경우다.
		if(code == null) {
			//Provider.supports() 메서드로 알맞은 인증제공자에서 처리될 것
			manager.authenticate(
					new UsernamePasswordAuthentication(username, password));
			
			return;
		} 
		//두번 째 인증
		manager.authenticate(new OtpAuthentication(username, code));
		//JJWT 인증
		/*
		 * 이 키는 비즈니스 논리 서버만 알고 있다.
		 * 비즈니스 논리 서버는 토큰에 서명하고 클라이언트가 엔드포인트를 호출할 때
		 * 같은 키로 토큰을 검증할 수 있다.
		 * 
		 * 여기선 예시를 위해 모든 사용자가 하나의 키를 사용하지만,
		 * 사용자별로 다른 키를 사용해야 한다.
		 * 
		 * 사용자별 키를 사용할 시 장점은 한 사용자 토큰을 무효화할 경우,
		 * 해당 키만 변경하면 된다.
		 */
		SecretKey key = Keys.hmacShaKeyFor(
				signingKey.getBytes(StandardCharsets.UTF_8));
		
		String jwt = Jwts.builder()
				.setClaims(Map.of("username", username)) //key:value 형식으로 몸통이다.
				.signWith(key)
				.compact();
		//권한 부여 헤더에 추가
		response.setHeader("Authorization", jwt);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		//login만 적용한다.
		return !request.getServletPath().equals("/login");
	}
}
