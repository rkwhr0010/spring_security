package com.springsecurity.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class NameService {
	
	//SpEL을 인자로 받는다
	@PreAuthorize("hasAuthority('write')")
	public String getName() {
		return "Fantastico";
	}
}
