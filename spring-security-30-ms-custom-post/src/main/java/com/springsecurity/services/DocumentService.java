package com.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import com.springsecurity.model.Document;
import com.springsecurity.repository.DocumentRepository;

@Service
public class DocumentService {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	//반환 값에 접근하는 사용자가 ROLE_admin 역할인 사람만 허용한다.
	@PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
	public Document getDocument(String code) {
		return documentRepository.findDocument(code);
	}
}
