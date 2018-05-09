package com.liuhe.redpacket.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用正则表达式的一些工具
 * 
 * @author ozil
 *
 */
public class RegexUtil {

	 private RegexUtil() {
	 }
	/**
	 * 获取对应标签里的内容
	 * 
	 * @param lable
	 *            要获取的标签名
	 * @param html
	 *            html页面字符串
	 * @return 所有该标签内容的集合
	 */
	public static List<String> findByLable(String lable, String html) {
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile("(?:" + lable + "=\"?)(.*?)\"?\\s");
		Matcher m = p.matcher(html);
		while (m.find()) {
			String group = m.group(1);
			list.add(group.substring(0, group.indexOf("\"") == -1 ? group.length() : group.indexOf("\"")));
		}
		return list;
	}
}
