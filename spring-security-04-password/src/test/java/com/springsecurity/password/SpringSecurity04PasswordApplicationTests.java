package com.springsecurity.password;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import com.springsecurity.password.encoders.PlainTextPasswordEncoder;

class SpringSecurity04PasswordApplicationTests {
	
	private void testPasswordEncoder(PasswordEncoder encoder) {
		String password = "tiger";
		assertThat(encoder.matches(password,encoder.encode(password))).isTrue();
	}
	
	@Test
	void 단순_암호_인코더_테스트() {
		System.out.println(1<<3);
		testPasswordEncoder(new PlainTextPasswordEncoder());
	}
	@Test
	void 암호_인코더_테스트_1() {
		testPasswordEncoder(NoOpPasswordEncoder.getInstance());
	}
	@Test
	void 암호_인코더_테스트_2() {
		testPasswordEncoder(new BCryptPasswordEncoder());
	}
	@Test
	void 암호_인코더_테스트_3() {
//		new SCryptPasswordEncoder(
//				65536  // CPU 비용
//				, 8    // 메모리 비용
//				, 1    // 병렬화 계수
//				, 32   // 키 길이
//				, 16); // 솔트 길이 (인코딩 시 추가로 들어가는 정보)
		testPasswordEncoder(SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
	}
}
