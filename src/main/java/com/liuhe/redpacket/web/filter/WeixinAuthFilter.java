package com.liuhe.redpacket.web.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.service.IUserService;
import com.liuhe.redpacket.utils.HttpClientUtil;
import com.liuhe.redpacket.utils.WeixinContants;

public class WeixinAuthFilter implements Filter {
	private IUserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext sc = filterConfig.getServletContext();
		WebApplicationContext cxt = WebApplicationContextUtils
				.getWebApplicationContext(sc);
		if (cxt != null && cxt.getBean("userServiceImpl") != null
				&& userService == null) {
			userService = (IUserService) cxt.getBean("userServiceImpl");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		String agent = hRequest.getHeader("User-Agent");
		// 如果session中已经存在微信号了，就不用获取了，否则要获取，获取到以后要存放sesion
		String openid = (String) hRequest.getSession().getAttribute("openid");
		if (openid == null) {
			// 只有在微信端才做里面的操作
			if (agent != null
					&& agent.toLowerCase().indexOf("micromessenger") >= 0) {
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				// 如果code不为空，scope为base,scope为userInfo代表用户已经同意
				if (code != null && state != null && state.equals("1")) {
					System.out.println("1111111111111");
					// 通过Code获取openid来进行授权
					String url = WeixinContants.GET_OPENID_URL
							.replace("APPID", WeixinContants.APPID)
							.replace("SECRET", WeixinContants.APPSECRET)
							.replace("CODE", code);
					String json = HttpClientUtil.httpGet(url);
					JSONObject jsonObject = JSONObject.parseObject(json);
					openid = jsonObject.getString("openid");
					String refresh_token = jsonObject
							.getString("refresh_token");
					System.out.println("=========微信授权参数==========" + json);
					// 刷新token
					url = WeixinContants.REFRESH_TOKEN_URL.replace("APPID",
							WeixinContants.APPID).replace("REFRESH_TOKEN",
							refresh_token);
					json = HttpClientUtil.httpGet(url);
					System.out.println("=========刷新token==========" + json);
					jsonObject = JSONObject.parseObject(json);
					String access_token = jsonObject.getString("access_token");
					// 拉取用户信息
					url = WeixinContants.GET_USERINFO_URL.replace("OPENID",
							openid).replace("ACCESS_TOKEN", access_token);
					json = HttpClientUtil.httpGet(url);
					json = new String(json.getBytes("ISO-8859-1"), "UTF-8");
					jsonObject = JSONObject.parseObject(json);
					System.out
							.println("===================userService============="
									+ userService);
					if (userService != null) {
						userService.saveOrUpdateUser(jsonObject);
					}

					String nickname = jsonObject.getString("nickname");
					String sex = jsonObject.getString("sex");
					String province = jsonObject.getString("province");
					String city = jsonObject.getString("city");
					String country = jsonObject.getString("country");
					String headimgurl = jsonObject.getString("headimgurl");
					hRequest.getSession().setAttribute("openid", openid);
					hRequest.getSession().setAttribute("nickname", nickname);
					hRequest.getSession().setAttribute("sex", sex);
					hRequest.getSession().setAttribute("province", province);
					hRequest.getSession().setAttribute("city", city);
					hRequest.getSession().setAttribute("country", country);
					hRequest.getSession()
							.setAttribute("headimgurl", headimgurl);
				} else {
					System.out.println("2222222222222222");
					// 发送用户同意的请求
					String path = hRequest.getRequestURL().toString();
					String query = hRequest.getQueryString();
					if (query != null) {
						path = path + "?" + query;
					}
					System.out.println(path);
					String uri = WeixinContants.AUTHORIZE_URL
							.replace("APPID", WeixinContants.APPID)
							.replace("REDIRECT_URI",
									URLEncoder.encode(path, "UTF-8"))
							.replace("SCOPE", "snsapi_userinfo")
							.replace("STATE", "1");
					hResponse.sendRedirect(uri);
					return;
				}
			}
		}
		chain.doFilter(hRequest, hResponse);
	}

	@Override
	public void destroy() {

	}

}
