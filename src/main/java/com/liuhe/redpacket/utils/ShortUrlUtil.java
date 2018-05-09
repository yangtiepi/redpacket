package com.liuhe.redpacket.utils;

import com.liuhe.redpacket.exception.LogicException;

public class ShortUrlUtil {
//	private static final String apikey = "yKbkTjcpXi";//03x.me短网址
//	private static final String secretkey = "gLGEJFgIVxBICnACwZAeoLrtWsmxOlsa";//03x.me
	private static final String sinaApikey = "2564594594";//新浪短网址

	/**
	 * 生成短网址
	 * 
	 */
	public static String localShortUrl(String url) {
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		String key = "test";
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z"

		};
		// 对传入网址进行 MD5 加密
		String hex = MD5Util.MD5(key + url);

		// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
		String sTempSubString = hex.substring(8, 8 + 8);

		// 这里需要使用 Long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用Long
		// ，则会越界
		Long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
		StringBuilder shortUrl = new StringBuilder("/");
		for (int j = 0; j < 6; j++) {
			// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
			long index = 0x0000003D & lHexLong;
			// 把取得的字符相加
			shortUrl.append(chars[(int) index]);
			// 每次循环按位右移 5 位
			lHexLong = lHexLong >> 5;
		}
		return ReadProperties.getBaseUrl() + shortUrl.append(".wht").toString();
	}

	/**
	 * 0x3.me生成短网址
	 * 
	 */
//	public static String shortUrl(String url) {
//		String sign = MD5Util.md5("apikey=" + apikey + "longurl=" + url
//				+ secretkey);
//		String result = HttpRequest.sendPost("https://0x3.me/api/add",
//				"apikey=" + apikey + "&longurl=" + url + "&sign=" + sign);
//		JSONObject jsonObject = new JSONObject(result);
//		Integer status = (Integer) jsonObject.get("status");
//		System.out.println(result);
//		if (status == 0) {
//			throw new LogicException("生成短网址失败，请重试！");
//		}
//		JSONObject data = jsonObject.getJSONObject("data");
//		return (String) data.get("shorturl");
//	}
}
