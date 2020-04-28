package com.seud.pub.saas.auth.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 鉴权信息
 * @author eddie
 * @version 1.0.0
 */
public class AuthorizationBean implements RequestConstants{
	/**
	 * 需要设置的http请求的头部信息
	 */
	private Map<String,String> requestHeaders = new HashMap<>(); 
	/**
	 * 请求内容
	 */
	private String requestBody;
	private SignedHeaders signedHeaders;
	private Credential credential;
	private String signature;
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Credential getCredential() {
		return credential;
	}
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	public SignedHeaders getSignedHeaders() {
		return signedHeaders;
	}
	public void setSignedHeaders(SignedHeaders signedHeaders) {
		this.signedHeaders = signedHeaders;
	}
	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}
	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	@Override
	public String toString() {
		if (credential != null) {
			StringBuilder str = new StringBuilder(credential.getSignatureMethod());
			str.append(" ")
			   .append("Credential=")
			   .append(credential.getSecretId())
			   .append("/")
			   .append(credential.getDate())
			   .append("/")
			   .append(credential.getService())
			   .append("/")
			   .append(REQUEST_END_STR)
			   .append(",SignedHeaders=")
			   .append(signedHeaders.toString())
			   .append(",Signature=")
			   .append(signature);
			return str.toString();
		} else {
			return super.toString();
		}
		
	}
}
