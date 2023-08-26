package com.springsecurity.password;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class Test {
	public static void main(String[] args) {
		useDelegatingPasswordEncoder();
		System.out.println("\n####커스텀####\n");
		userCustomDelegatingPasswordEncoder();
	}

	private static void userCustomDelegatingPasswordEncoder() {
		PasswordEncoder encoder = createDelegatingPasswordEncoder();
		String pw = "tiger";
		System.out.println(encoder.encode(pw));
		System.out.println(encoder.matches(pw, encoder.encode(pw)));
	}
	
	private static void useDelegatingPasswordEncoder() {
		var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		final var pw = "tiger";
		//기본적으로, 인코딩은 객체 생성 시에 저장되어 변경할 수 없다. 기본 동작 BCryptPasswordEncoder
		System.out.println(encoder.encode(pw));
		System.out.println(encoder.matches(pw, encoder.encode(pw)));
		
		//matches()는 접두에 따라 가변 인코딩을 지원한다.
		var encoder2 = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
		System.out.println("{scrypt}" + encoder2.encode(pw));
		System.out.println(encoder.matches(pw, "{scrypt}" + encoder2.encode(pw)));
	}
	
	public static PasswordEncoder createDelegatingPasswordEncoder() {
		String encodingId = "custom";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(encodingId, new CustomPasswordEncoder());
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("scrypt@SpringSecurity_v5_8", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		return new DelegatingPasswordEncoder(encodingId, encoders);
	}
}

//커스텀 구현체
class CustomPasswordEncoder implements PasswordEncoder{
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return Objects.equals(rawPassword, encodedPassword);
	}
}
