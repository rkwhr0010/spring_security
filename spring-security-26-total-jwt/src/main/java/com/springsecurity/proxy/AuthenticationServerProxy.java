package com.springsecurity.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.springsecurity.models.User;

@Component
public class AuthenticationServerProxy {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${auth.server.base.url}")
	private String baseUrl;
	
	public void sendAuth(String username, String password) {
		//인증 서버 경로
		String url = baseUrl + "/user/auth";
		
		//첫 번째 인증 수행
		var body = new User();
		body.setUsername(username);
		body.setPassword(password);
		
		var request = new HttpEntity<>(body);
		
		restTemplate.postForEntity(url, request, Void.class);
	}
	
	public boolean sendOTP(String username, String code) {
		//인증 서버 경로
		String url = baseUrl + "/otp/check";
		
		//두 번째 인증 수행
		var body = new User();
		body.setUsername(username);
		body.setCode(code);
		
		var request = new HttpEntity<>(body);
		
		ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);
		//정상 응답 받으면, true 리턴
		return response.getStatusCode().equals(HttpStatus.OK);
	}
}
