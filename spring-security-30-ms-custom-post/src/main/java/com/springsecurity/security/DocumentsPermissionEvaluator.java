package com.springsecurity.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springsecurity.model.Document;

@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator{

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		
		//returnObject, 즉 Document
		var document = (Document) targetDomainObject;
		//ROLD_admin
		String p = (String) permission;
		
		boolean admin = authentication.getAuthorities()
			.stream()
			.anyMatch(a -> a.getAuthority().equals(p));
		
		//운영자이거나 문서 소유자면 true
		return admin || 
				document.getOwner().equals(authentication.getName());
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		//이 예제에선 첫번 째것만 필요
		return false;
	}
}
