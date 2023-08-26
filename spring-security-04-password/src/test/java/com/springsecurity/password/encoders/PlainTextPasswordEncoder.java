package com.springsecurity.password.encoders;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainTextPasswordEncoder implements PasswordEncoder{
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return Objects.equals(rawPassword, encodedPassword);
	}
}
