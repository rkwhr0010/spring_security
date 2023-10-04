package com.springsecurity.model;

public class Document {
	private String owner;

	public Document(String owner) {
		super();
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
