package com.seud.pub.saas.auth.bean;

/**
 * 签名凭证
 * @author eddie
 * @version 1.0
 */
public class Credential {
	private String signatureMethod = "HmacSHA256";
	private String secretId;
	private String date;
	private String service;
	
	public String getSignatureMethod() {
		return signatureMethod;
	}
	public void setSignatureMethod(String signatureMethod) {
		this.signatureMethod = signatureMethod;
	}
	public String getSecretId() {
		return secretId;
	}
	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
}
