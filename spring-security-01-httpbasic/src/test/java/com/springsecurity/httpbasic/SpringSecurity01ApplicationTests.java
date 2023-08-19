package com.springsecurity.httpbasic;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurity01ApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	void HTTP_BASIC_인증실패() throws Exception {
		mvc.perform(get("/resource"))
			.andExpect(status().isUnauthorized()); // 401
	}
	@Test
	void HTTP_BASIC_인증성공() throws Exception {
		mvc.perform(get("/resource")
					.with(httpBasic("scott", "tiger")))
				.andExpect(content().string("resource"))
				.andExpect(status().isOk());
	}
	@Test
	@WithMockUser(username = "root", password = "root")
	void HTTP_BASIC_테스트계정_인증성공() throws Exception {
		mvc.perform(get("/resource")
				.with(httpBasic("root", "root")))
		.andExpect(content().string("resource"))
		.andExpect(status().isOk());
	}
	
	@Test
	void PasswordEncoder_테스트() {
		String password = "12345";
		assertThat(encoder.matches(password, encoder.encode(password))).isEqualTo(Boolean.TRUE);
	}
}