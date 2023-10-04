package com.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.springsecurity.model.Document;
import com.springsecurity.repository.DocumentRepository;

@Service
public class DocumentService {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	//두 번째 형식
	@PreAuthorize("hasPermission(#code, 'document', 'ROLE_admin')")
	public Document getDocument(String code) {
		return documentRepository.findDocument(code);
	}
}
