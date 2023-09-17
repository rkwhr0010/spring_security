package com.springsecurity.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springsecurity.authentication.UsernamePasswordAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Value("${jwt.signing.key}")
	private String signingKey;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = request.getHeader("Authorization");
		
		SecretKey key = Keys.hmacShaKeyFor(
				signingKey.getBytes(StandardCharsets.UTF_8));
		
		//JWT 파싱
		JwtParser jwtParser = Jwts.parserBuilder()
			.setSigningKey(key)
			.build();
		//서명된 JWT, JSON Web Token Signed
		Jws<Claims> jws = jwtParser.parseClaimsJws(jwt);
		
		Claims claims = jws.getBody();
		
		String username = claims.get("username").toString();
		var authority = new SimpleGrantedAuthority("user");
		
		//완전히 인증된 인증 객체
		var authentication = 
				new UsernamePasswordAuthentication(username, null, List.of(authority));
		//완료된 인증 객체는 SecurityContext에 저장해야 한다.
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// login 만 빼고, 전부 동작하도록
		return request.getServletPath().equals("/login");
	}
}
