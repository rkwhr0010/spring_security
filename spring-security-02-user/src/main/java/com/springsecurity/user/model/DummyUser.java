package com.springsecurity.user.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DummyUser implements UserDetails{
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return List.of(() -> "read"); // 비권장
		return List.of(new SimpleGrantedAuthority("read"));
	}
	public String getPassword() {
		return "tiger";
	}
	public String getUsername() {
		return "scott";
	}
	public boolean isAccountNonExpired() { return true;	}
	public boolean isAccountNonLocked() { return true;	}
	public boolean isCredentialsNonExpired() { return true;	}
	public boolean isEnabled() { return true;}
}
