package com.seud.pub.saas.auth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import com.seud.pub.saas.auth.bean.AuthorizationBean;
import com.seud.pub.saas.auth.bean.Credential;
import com.seud.pub.saas.auth.bean.RequestBean;
import com.seud.pub.saas.auth.bean.RequestConstants;
import com.seud.pub.saas.auth.bean.SignedHeaders;
import com.seud.pub.saas.auth.utils.AesUtils;
import com.seud.pub.saas.auth.utils.SignatureTool;

/**
 * 授权信息实例工厂类
 * @author eddie
 * @version 1.0.0
 */
public final class AuthorizationFactory implements RequestConstants{
	private AuthorizationFactory() {};
	
	/**
	 * 获取一个授权信息实例
	 * @param reqeustUrlHost,saas服务主机名
	 * @param methodName，要访问的接口方法地址例如/search/company
	 * @param version，接口版本号
	 * @param jsonParameters，接口方法的参数json格式
	 * @param parametersEncrypt，接口方法是否要求参数加密
	 * @param appId，saas平台分配的应用id
	 * @param appSecret，saas平台分配的应用密钥
	 * @return
	 * @throws Throwable 
	 */
	public static RequestBean getInstance(String requestUrlHost,String methodName,String version,String jsonParameters,boolean parametersEncrypt,String appId,String appSecret) throws Throwable {
		final AuthorizationBean bean = new AuthorizationBean();
		
		if (parametersEncrypt) {
			bean.setRequestBody(AesUtils.encrypt(jsonParameters, appSecret.substring(0,16)));
		} else {
			bean.setRequestBody(jsonParameters);
		}
		Date curDate = new Date();
		long timestamp = curDate.getTime();
		String utcDate = getUTCDate(timestamp);
		SignedHeaders signedHeaders = new SignedHeaders(HEAD_CONTENT_TYPE, HEAD_HOST, HEAD_ACTION, HEAD_NONCE,HEAD_TIMESTAMP);
		bean.setSignedHeaders(signedHeaders);
		// 签名凭证
		Credential credential = new Credential();
		credential.setDate(utcDate);
		credential.setSecretId(appId);
		credential.setService(methodName);
		bean.setCredential(credential);
		
		Map<String,String> requestHeaders = new HashMap<>();
		requestHeaders.put(HEAD_HOST, requestUrlHost);
		requestHeaders.put(HEAD_CONTENT_TYPE,"application/json");
		requestHeaders.put(HEAD_ACTION,methodName);
		requestHeaders.put(HEAD_NONCE, randomId(5));
		requestHeaders.put(HEAD_SECRETID, appSecret);
		requestHeaders.put(HEAD_TIMESTAMP,String.valueOf(timestamp));
		requestHeaders.put(HEAD_TOKEN, "");
		requestHeaders.put(HEAD_VERSION, version);
		
		String canonicalHeader = buildCanonicalHeader(signedHeaders,requestHeaders);
		String signature = SignatureTool.buildSignature(canonicalHeader, signedHeaders.toString(), jsonParameters,  
				utcDate, methodName, String.valueOf(timestamp), appSecret);
		bean.setSignature(signature);
		requestHeaders.put(HEAD_AUTHORIZATION, bean.toString());
		bean.setRequestHeaders(requestHeaders);
		
		RequestBean requestBean = new RequestBean();
		requestBean.setRequestBody(bean.getRequestBody());
		requestBean.setRequestHeaders(requestHeaders);
		return requestBean;
	}
	
	public static String randomId(int length) {
		String id = "";
		for (int i = 0; i < length; i++) {
			id += new Random().nextInt(9);
		}
		return id;
	}
	
	public static String getUTCDate(long time) {
		String timestamp = String.valueOf(time / 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = sdf.format(new Date(Long.valueOf(timestamp + "000")));
        return date;
	}
	
	private static String buildCanonicalHeader(SignedHeaders signedHeaders,Map<String,String> rb) {
		StringBuilder canonicalHeaderStr = new StringBuilder();
		String[] headers = signedHeaders.getHeaders();
		
		for (String header : headers) {
			canonicalHeaderStr.append(header).append(":").append(rb.get(header)).append("\n");
		}
		return canonicalHeaderStr.toString();
	}
}
