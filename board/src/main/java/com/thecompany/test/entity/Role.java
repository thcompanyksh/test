package com.thecompany.test.entity;


public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
	
	private String label;
	
	private Role(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
}