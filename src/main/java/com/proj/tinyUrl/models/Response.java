package com.proj.tinyUrl.models;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class Response {

	private HttpStatus statusCode;
	private String message;
	private HashMap<String, Object> additionalData;
	
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus ok) {
		this.statusCode = ok;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HashMap<String, Object> getAdditionalData() {
		return additionalData;
	}
	public void setAdditionalData(HashMap<String, Object> additionalData) {
		this.additionalData = additionalData;
	}
	
	
	
}
