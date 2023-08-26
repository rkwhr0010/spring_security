package com.springsecurity.user.model;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserBuilderEx {
	UserDetails example() {
		return User.withUsername("scott")
			.password("tiger")
			.passwordEncoder(pw -> pw)
			.authorities("read")
			.disabled(true)
			.accountExpired(true)
			.accountLocked(true)
			.credentialsExpired(true)
			.build();
	}
}
