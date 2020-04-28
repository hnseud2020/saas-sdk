package com.seud.pub.saas.auth.utils;

import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Sha256加密工具类
 * @author eddie
 * @version 1.0
 */
public final class Sha256Tool {
	/**
	 * sha256
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String sha256(String str) throws Exception {
		MessageDigest messageDigest;
		messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(str.getBytes("UTF-8"));
		return byte2Hex(messageDigest.digest()).toLowerCase();
	}
	
	/**
	 * 数字签名
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String sign(String data,String key) throws Exception {
		SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        return byte2Hex(mac.doFinal(data.getBytes()));
	}

	/**
	 * 将byte转为16进制 
	 * @param bytes
	 * @return
	 */
	private static String byte2Hex(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				// 1得到一位的进行补0操作
				stringBuffer.append("0");
			}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}
}
