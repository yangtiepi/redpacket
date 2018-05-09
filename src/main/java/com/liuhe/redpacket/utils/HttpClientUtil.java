package com.liuhe.redpacket.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient相关操作都放到里面
 *    get请求
 *    Post请求
 * @author admin
 *
 */
public class HttpClientUtil {

	/**
	 *  使用HTTPClient执行Get请求
	 * @param url get请求所对应的url地址
	 * @return 返回值为执行后json响应结果
	 */
	public static String httpGet(String url) {
		try {
			//创建执行对象
			HttpClient execute = new DefaultHttpClient(); 
			//创建请求对象
			HttpGet httpGet = new HttpGet(url);
			//通过执行对象执行请求，并获取响应对象response
			HttpResponse response = execute.execute(httpGet);
			System.out.println("响应状态码为："+response.getStatusLine().getStatusCode());
			//对结果进行处理
			HttpEntity responseEntity = response.getEntity();
			return EntityUtils.toString(responseEntity);//把响应的内容转换为json并返回
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String httpPost(String url, String menuStr) {
		try {
			//创建执行对象
			HttpClient execute = new DefaultHttpClient(); 
			//创建请求对象
			HttpPost httpPost = new HttpPost(url);
			//设置请求的参数
			httpPost.setEntity(new StringEntity(menuStr, "utf-8"));
			//通过执行对象执行请求，并获取响应对象response
			HttpResponse response = execute.execute(httpPost);
			System.out.println("响应状态码为："+response.getStatusLine().getStatusCode());
			//对结果进行处理
			HttpEntity responseEntity = response.getEntity();
			return EntityUtils.toString(responseEntity);//把响应的内容转换为json并返回
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
