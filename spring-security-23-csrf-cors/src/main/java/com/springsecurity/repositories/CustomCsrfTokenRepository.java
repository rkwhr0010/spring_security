package com.springsecurity.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import com.springsecurity.entities.Token;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository{
	private final String CSRF = "_csrf";
	private final String X_CSRF_TOKEN = "X-CSRF-TOKEN";
	private final String X_IDENTIFIER = "X-IDENTIFIER";
	
	@Autowired
	private JpaTokenRepository tokenRepository;
	//매 번 새로운 토큰을 생성하는 책임을 가진다.
	public CsrfToken generateToken(HttpServletRequest request) {
		return new DefaultCsrfToken(X_CSRF_TOKEN, CSRF, UUID.randomUUID().toString());
	}
	
	//클라이언트 마다 생성된 토큰을 저장하는 책임
	public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
		if (findToken(request).isPresent()) {
			//이 전에 해당 식별자로 토큰을 만든적 있다.
			Token token = findToken(request).get();
			token.setToken(token.getToken());
		} else {
			//토큰을 만든 적 없다. 즉, 최초 토큰을 의미한다.
			Token token = new Token();
			token.setToken(csrfToken.getToken());
			token.setIdentifier(request.getHeader(X_IDENTIFIER));
			tokenRepository.save(token);
		}
	}

	//토큰 세부 정보가 있으면 로드, 없으면 null리턴
	public CsrfToken loadToken(HttpServletRequest request) {
		var token = findToken(request);
		if (token.isPresent()) {
			return new DefaultCsrfToken(X_CSRF_TOKEN, CSRF, token.get().getToken());
		} else {
			return null;
		}
	}
	
	private Optional<Token> findToken(HttpServletRequest request) {
		//이 헤더 값을 사용하기로 가정
		return tokenRepository.findByIdentifier(request.getHeader(X_IDENTIFIER));
	}

}
