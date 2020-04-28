package com.seud.pub.saas.auth.utils;

import com.seud.pub.saas.auth.bean.RequestConstants;

/**
 * 数据签名工具类
 * @author eddie
 * @version 1.0
 */
public final class SignatureTool implements RequestConstants {
	/**
	 * 构建签名
	 * @param canonicalHeader,参与签名的头部信息
	 * @param signedHeaders,参与签名的头部参数名称
	 * @param requestBody,请求内容
	 * @param algorithm,签名算法
	 * @param date,日期
	 * @param service，服务名
	 * @param requestTimestamp，时间戳
	 * @param secretKey,密钥
	 * @return
	 * @throws Exception
	 */
	public static String buildSignature(String canonicalHeader,String signedHeaders,String requestBody,
			String date,String service,String requestTimestamp,String secretKey) throws Exception {
		String canonicalRequest = buildCanonicalRequest(
				canonicalHeader,
				signedHeaders,
				requestBody);
	    String stringToSign = buildSignature(requestTimestamp, canonicalRequest);
	    String secretDate = Sha256Tool.sign("SEUD" + secretKey, date);
	    String secretService = Sha256Tool.sign(secretDate, service);
	    String secretSigning = Sha256Tool.sign(secretService, REQUEST_END_STR);
	    String signature = Sha256Tool.sign(secretSigning, stringToSign);
	    return signature;
	}
	
	/**
	 * 构建规范请求串
	 * @param canonicalHeaders 参与签名的头部信息，至少包含 host 和 content-type 两个头部，</br>
	 * 也可加入自定义的头部参与签名以提高自身请求的唯一性和安全性。拼接规则：1）头部 key 和 value</br>
	 * 统一转成小写，并去掉首尾空格，按照 key:value\n 格式拼接；2）多个头部，按照SignedHeaders中的排序进行拼接。
	 * @param signedHeaders,参与签名的头部参数名称
	 * @param hashedRequestPayload
	 * @return
	 * @throws Exception 
	 */
	public static String buildCanonicalRequest(String canonicalHeaders,String signedHeaders,String requestPayload) throws Exception {
		String httpRequestMethod = "POST";
		String canonicalUri = "/";
		StringBuilder canonicalRequest = new StringBuilder(httpRequestMethod);
		canonicalRequest.append("\n");
		canonicalRequest.append(canonicalUri);
		canonicalRequest.append("\n");
		canonicalRequest.append(canonicalHeaders);
		canonicalRequest.append("\n");
		canonicalRequest.append(signedHeaders);
		canonicalRequest.append("\n");
		canonicalRequest.append(Sha256Tool.sha256(requestPayload));
		return canonicalRequest.toString();
	}
	
	/**
	 * 构建签名字符串
	 * @param algorithm,签名算法
	 * @param requestTimestamp，时间戳
	 * @param canonicalRequest，规范请求串
	 * @return
	 * @throws Exception
	 */
	public static String buildSignature(String requestTimestamp,String canonicalRequest) throws Exception {
		StringBuilder stringToSign = new StringBuilder("HmacSHA256");
		stringToSign.append("\n");
		stringToSign.append(requestTimestamp);
		stringToSign.append("\n");
		stringToSign.append(Sha256Tool.sha256(canonicalRequest));
		return stringToSign.toString();
	}
}
