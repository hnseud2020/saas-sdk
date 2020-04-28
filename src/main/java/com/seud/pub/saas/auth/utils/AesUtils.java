package com.seud.pub.saas.auth.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * aes加解密工具类
 * @author eddie
 *
 */
public final class AesUtils {
	/**
	 * 解密
	 * @throws Throwable 
	 */
	public static String decrypt(String sSrc, String sKey) throws Throwable {
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] encrypted1 = hex2byte(sSrc);
		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original,"UTF-8");
		return originalString;
	}

	/**
	 * 
	 * @param sSrc
	 * @param 加密
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String sSrc, String sKey) throws Throwable{
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
		return byte2hex(encrypted).toLowerCase();
	}
	
	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	private static byte[] hex2byte(String strhex) {
		if (strhex == null) {
			return null;
		}
		int l = strhex.length();
		if ((l & 1) == 1) {
			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
					16);
		}
		return b;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0X000000FF));
			if (stmp.length() == 1) {
				hs.append("0");
			} 
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}
	
	public static void main(String[] args) throws Throwable {
		System.out.println(encrypt("7896541236654789","58DD5DC3658BD281"));
		System.out.println(encrypt("{'a':'b','c':'d'}","68DD5DC3658BD284"));
	}
}
