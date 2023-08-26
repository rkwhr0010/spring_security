package com.springsecurity.user.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SpringSecurityUserDetails implements UserDetails{
	private final JpaUser user;
	
	public SpringSecurityUserDetails(JpaUser user) {
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities().stream()
					.map(Authority::getName)
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
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
	public JpaUser getUser() {
		return user;
	}
}
