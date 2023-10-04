package com.springsecurity.repository;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.springsecurity.model.Document;

@Service
public class DocumentRepository {
	
	private Map<String, Document> documents = 
			Map.of(
					"abc123", new Document("natalie"),
					"qwe123", new Document("nataile"),
					"asd555", new Document("emma")
					);
	
	public Document findDocument(String code) {
		return documents.get(code);
	}
}
