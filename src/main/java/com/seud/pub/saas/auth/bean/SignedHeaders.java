package com.seud.pub.saas.auth.bean;

/**
 * 参与签名的头部
 * @author eddie
 * @version 1.0
 */
public class SignedHeaders {
	private String[] headers;
	
	public SignedHeaders() {
		
	}
	
	public SignedHeaders(String ... headers) {
		this.headers = headers;
	}
	
	/**
	 * 检查参与签名的头部名称是否存在
	 * @param name
	 * @return
	 */
	public boolean exist(String name) {
		return exist(new String[] {name});
	}
	
	/**
	 * 检查参与签名的头部名称是否存在
	 * @param name
	 * @return
	 */
	public boolean exist(String ... name) {
		if (headers != null) {
			for (String header : headers) {
				for (String n : name) {
					if (header.equals(n)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		if (headers != null) {
			StringBuilder str = new StringBuilder();
			int length = headers.length;
			
			for (int i = 0; i < length; i++) {
				str.append(headers[i]);
				
				if (i < length - 1) {
					str.append(";");
				}
			}
			return str.toString();
		} else {
			return super.toString();
		}
	}

	public String[] getHeaders() {
		return headers;
	}
	public String[] getHeaders(String headerStr) {
		headers = headerStr.split(";");
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	public void setHeaders(String headerStr) {
		this.headers = headerStr.split(";");
	}
}
