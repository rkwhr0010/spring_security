package com.springsecurity.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springsecurity.model.Document;
import com.springsecurity.repository.DocumentRepository;

@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator{
	
	@Autowired
	private DocumentRepository repository;

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		
		//#code
		String code = targetId.toString();
		//검색
		Document document = repository.findDocument(code);
		//롤 ROLE_admin
		String p = (String) permission;
		
		boolean admin = authentication.getAuthorities()
				.stream()
				.anyMatch(a -> a.getAuthority().equals(p));
		
		return admin || document.getOwner().equals(authentication.getName());
	}
}
