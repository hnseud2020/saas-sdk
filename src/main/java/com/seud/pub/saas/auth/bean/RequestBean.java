package com.seud.pub.saas.auth.bean;

import java.util.Map;

public class RequestBean {
	private String requestBody;
	private Map<String,String> requestHeaders;
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}
	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	
}
