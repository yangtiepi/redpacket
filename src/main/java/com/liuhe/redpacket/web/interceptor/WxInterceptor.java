package com.liuhe.redpacket.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.liuhe.redpacket.service.IUserService;
import com.liuhe.redpacket.utils.HttpClientUtil;
import com.liuhe.redpacket.utils.WeixinContants;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * 系统后台拦截器
 * 
 * @author ozil
 *
 */
@Component
public class WxInterceptor implements HandlerInterceptor {
    private final Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private IUserService userService;

	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行
	 * ，而且所有的Interceptor中的preHandle方法都会在
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的
	 * ，这种中断方式是令preHandle的返 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 * 
	 * 1.在调用控制器方法前，拦截 返回值为false，代表拦截 返回值为true，代表放行
	 * 
	 */
	/**
	 * 登陆权限
	 */

	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，
	 * 而且所有的Interceptor中的preHandle方法都会在
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，
	 * 这种中断方式是令preHandle的返 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 * 
	 * 1.在调用控制器方法前，拦截 返回值为false，代表拦截 返回值为true，代表放行
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String agent = request.getHeader("User-Agent");
		log.info("=========进入微信拦截器======agent====" + agent);
		// 如果session中已经存在微信号了，就不用获取了，否则要获取，获取到以后要存放sesion
		String openid = (String) request.getSession().getAttribute("openid");
		log.info("=========进入微信拦截器======openid====" + openid);
//		if (openid == null)request.getSession().setAttribute("openid","o4HNe0iimVnHuAbhA0p3_SkHqnb4");
		if (openid == null) {
			// 只有在微信端才做里面的操作
			if (agent != null
					&& agent.toLowerCase().indexOf("micromessenger") >= 0) {
				String code = request.getParameter("code");
				String state = request.getParameter("state");
				// 如果code不为空，scope为base,scope为userInfo代表用户已经同意
				if (code != null && state != null && state.equals("1")) {
					log.info("1111111111111");
					// 通过Code获取openid来进行授权
					String url = WeixinContants.GET_OPENID_URL
							.replace("APPID", WeixinContants.APPID)
							.replace("SECRET", WeixinContants.APPSECRET)
							.replace("CODE", code);
					String json = HttpClientUtil.httpGet(url);
					JSONObject jsonObject = JSONObject.parseObject(json);
					openid = jsonObject.getString("openid");
					String access_token = jsonObject
							.getString("access_token");
					log.info("=========微信授权参数==========" + json);
					if (StringUtils.isBlank(access_token)) {
						String path = request.getRequestURL().toString();
						String query = request.getQueryString();
						if (query != null) {
							path = path + "?" + query;
						}
						log.info("=========授权失败重定向==========" + path);
						response.sendRedirect(path);
						return false;
					}
					// 拉取用户信息
					url = WeixinContants.GET_USERINFO_URL.replace("OPENID",
							openid).replace("ACCESS_TOKEN", access_token);
					json = HttpClientUtil.httpGet(url);
					json = new String(json.getBytes("ISO-8859-1"), "UTF-8");
					jsonObject = JSONObject.parseObject(json);
					log.info("===================userService============="
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
					request.getSession().setAttribute("openid", openid);
					request.getSession().setAttribute("nickname", nickname);
					request.getSession().setAttribute("sex", sex);
					request.getSession().setAttribute("province", province);
					request.getSession().setAttribute("city", city);
					request.getSession().setAttribute("country", country);
					request.getSession()
							.setAttribute("headimgurl", headimgurl);
				} else {
					log.info("2222222222222222");
					// 发送用户同意的请求
					String path = request.getRequestURL().toString();
					String query = request.getQueryString();
					if (query != null) {
						path = path + "?" + query;
					}
					log.info(path);
					String uri = WeixinContants.AUTHORIZE_URL
							.replace("APPID", WeixinContants.APPID)
							.replace("REDIRECT_URI",
									URLEncoder.encode(path, "UTF-8"))
							.replace("SCOPE", "snsapi_userinfo")
							.replace("STATE", "1");
					response.sendRedirect(uri);
					return false;
				}
			}else {
				String contextPath = request.getSession().getServletContext().getContextPath();
				response.sendRedirect(request.getServerName()+":"+request.getServerPort()+contextPath+"/mobile/wxFllow");
				return false;
			}
		}
		return true;// 放行
	}

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，
	 * 也就是DispatcherServlet渲染了视图执行，
	 * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
	 * 
	 * 3.在视图生成之后（后台所有所有逻辑都完成后）
	 */

	/**
	 * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，
	 * 它的执行时间是在处理器进行处理之
	 * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，
	 * 也就是说在这个方法中你可以对ModelAndView进行操
	 * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，
	 * 这跟Struts2里面的拦截器的执行过程有点像，
	 * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，
	 * Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
	 * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，
	 * 要在Interceptor之后调用的内容都写在调用invoke方法之后。
	 * 
	 * 2.在调用控制器方法后，拦截（在生成视图之前）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，
	 * 也就是DispatcherServlet渲染了视图执行，
	 * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
	 * 
	 * 3.在视图生成之后（后台所有所有逻辑都完成后）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
