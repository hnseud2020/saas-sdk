package com.seud.pub.saas.auth.bean;

/**
 * 请求参数常量
 * @author eddie
 * @version 1.0
 */
public interface RequestConstants {
	/**
	 * 请求头参数-操作指令
	 */
	String HEAD_ACTION = "SEUD-Action";
	/**
	 * 请求头参数-时间戳
	 */
	String HEAD_TIMESTAMP = "SEUD-Timestamp";
	/**
	 * 请求头参数-随机整数
	 */
	String HEAD_NONCE = "SEUD-Nonce";
	/**
	 * 请求头参数-接口版本号
	 */
	String HEAD_VERSION = "SEUD-Version";
	/**
	 * 请求头参数-HTTP 标准身份认证头部字段
	 */
	String HEAD_AUTHORIZATION = "Authorization";
	/**
	 * 请求头参数-临时鉴权预留通证字段
	 */
	String HEAD_TOKEN = "SEUD-Token";
	/**
	 * 请求头参数-签名加密的公钥(appKey)
	 */
	String HEAD_SECRETID = "SEUD-SecretId";
	/**
	 * 请求头参数-请求内容类型
	 */
	String HEAD_CONTENT_TYPE = "Content-Type";
	/**
	 * 客户端类型01-broker
	 */
	String HEAD_API_TYPE = "apiTypeCode";
	/**
	 * 请求头参数-请求的服务主机域名
	 */
	String HEAD_HOST = "Host";
	/**
	 * 请求时间偏差值3分钟
	 */
	long TIMESTAMP_OFFSET = 180000;
	/**
	 * 请求参数hash缓存主键
	 */
	String REQUEST_HASH_KEY = "req:hash";
	/**
	 * 凭证结束字符串
	 */
	String REQUEST_END_STR = "seud_request";
}
