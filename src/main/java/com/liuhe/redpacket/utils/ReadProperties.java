package com.liuhe.redpacket.utils;

import java.io.InputStream;
import java.util.Properties;
/**
 * 读取配置文件的工具
 * @author ozil
 *
 */
public class ReadProperties {
	//==================================短信相关====================================
	private static String url = null;// 请求地址
	private static String sid = null;// 主账户id
	private static String appId = null;// 应用id
	private static String token = null;// 验证信息
	//==================================APP客服电话============================================
	private static String phone = null;
	//==================================邀请码============================================
	private static String useCode = null;
	//==================================项目部署地址============================================
	private static String baseUrl = null;
	static {
		if (url == null) {
			url = getValue("url");
		}
		if (sid == null) {
			sid = getValue("sid");
		}
		if (appId == null) {
			appId = getValue("appId");
		}
		if (token == null) {
			token = getValue("token");
		}
		if (phone == null) {
			phone = getValue("phone");
		}
		if (useCode == null) {
			useCode = getValue("useCode");
		}
		if (baseUrl == null) {
			baseUrl = getValue("baseUrl");
		}
	}
	
	public static String getValue(String key) {
		InputStream is = ReadProperties.class
				.getResourceAsStream("/wht.properties");
		Properties dbproperties = new Properties();
		try {
			dbproperties.load(is);
			return dbproperties.getProperty(key).toString();

		} catch (Exception e) {
			System.err.println("不能读取属性文件. "
					+ "请确保db.properties在CLASSPATH指定的路径中");
		}
		return null;
	}

	public static String getUrl() {
		return url;
	}

	public static String getSid() {
		return sid;
	}

	public static String getAppId() {
		return appId;
	}
	public static String getToken() {
		return token;
	}
	/**
	 * APP客服电话
	 * @return
	 */
	public static String getPhone() {
		return phone;
	}
	/**
	 * 邀请码
	 * @return
	 */
	public static String getUseCode() {
		return useCode;
	}
	/**
	 * 项目部署地址
	 * @return
	 */
	public static String getBaseUrl() {
		return baseUrl;
	}
	
}
