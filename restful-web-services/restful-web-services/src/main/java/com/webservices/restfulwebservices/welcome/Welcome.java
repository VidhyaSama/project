package com.webservices.restfulwebservices.welcome;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("DynamicFilter")
public class Welcome {

	private String message;
	private String name;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Welcome(String message, String name) {
		super();
		this.message = message;
		this.name = name;
	}

	
	

}
