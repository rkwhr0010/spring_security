package com.springsecurity.models;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springsecurity.entities.Authority;
import com.springsecurity.entities.User;

//UserDetails 역할만 하는 래퍼 클래스
public class CustomUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
	//JPA 엔티티 역할만 하는 User
	private final User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities()
				.stream()
				.map(Authority::getName)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	public User getUser() {
		return user;
	}
	public String getPassword() {
		return user.getPassword();
	}
	public String getUsername() {
		return user.getUsername();
	}
	public boolean isAccountNonExpired() {
		return true;
	}
	public boolean isAccountNonLocked() {
		return true;
	}
	public boolean isCredentialsNonExpired() {
		return true;
	}
	public boolean isEnabled() {
		return true;
	}
}
