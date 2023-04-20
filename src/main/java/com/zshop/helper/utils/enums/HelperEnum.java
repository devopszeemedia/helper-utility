package com.zshop.helper.utils.enums;

public enum HelperEnum {

	BEARER("Bearer "),AUTHORIZATION("Authorization"), ACCEPT("Accept"), SESSION("Session");

	private String source;

	HelperEnum(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	
}
