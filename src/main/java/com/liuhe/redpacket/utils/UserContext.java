package com.liuhe.redpacket.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.liuhe.redpacket.domain.Employee;
import com.liuhe.redpacket.domain.Permission;
import com.liuhe.redpacket.domain.User;

/**
 * 用户上下文对象
 * 
 * @author Administrator
 *
 */

public class UserContext {

	public static final String EMPLOYEE_IN_SESSION = "employee_in_session";
	public static final String USER_IN_SESSION = "user_in_session";
	public static final String CUSTOMER_IN_SESSION = "customer_in_session";
	public static final String STORE_IN_SESSION = "store_in_session";
	public static final String PERMISSION_IN_SESSION = "permission_in_session";
	// 短信随机密码
	public static final String CUSTOMER_RANDOM_IN_SESSION = "customer_random_in_session";
	// 短信验证码
	public static final String USER_RANDOM_IN_SESSION = "user_random_in_session";

	public static HttpServletRequest getRequest() {
		// 从RequestContextHolder中，获取ServletRequestAttributes
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		// 从ServletRequestAttributes中，获取request
		HttpServletRequest request = requestAttributes.getRequest();
		return request;
	}

	private static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static HttpServletResponse getResponse() {
		// 从RequestContextHolder中，获取ServletRequestAttributes
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		// 从ServletRequestAttributes中，获取response
		HttpServletResponse response = requestAttributes.getResponse();
		return response;
	}

	public static void setEmployee(Employee emp) {
		getSession().setAttribute(EMPLOYEE_IN_SESSION, emp);
	}

	public static Employee getEmployee() {
		return (Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION);
	}
	public static void removeEmployee() {
		getSession().removeAttribute(EMPLOYEE_IN_SESSION);
	}
	public static void setUser(User user) {
		getSession().setAttribute(USER_IN_SESSION, user);
	}
	
	public static User getUser() {
		return (User) getSession().getAttribute(USER_IN_SESSION);
	}

	public static void removeUser() {
		getSession().removeAttribute(USER_IN_SESSION);
	}



	public static User getUserRandom() {
		return (User) getSession().getAttribute(USER_RANDOM_IN_SESSION);
	}
	public static void setUserRandom(User user) {
		getSession().setAttribute(USER_RANDOM_IN_SESSION, user);
	}
	
	public static String getIP() {
		return getRequest().getRemoteAddr();
	}

	// public static void setUser(Employee emp, HttpSession session){
	// session.setAttribute(USER_IN_SESSION, emp);
	// }
	//
	// public static Employee getUser(HttpSession session){
	// return (Employee) session.getAttribute(USER_IN_SESSION);
	// }

	public static boolean checkEmployeePermission(Permission permission) {
		// 获取所有权限
		List<Permission> userPermissions = UserContext.getEmployeePermission();
		// 如果session中没有保存权限就直接返回false
		if (userPermissions == null || userPermissions.size() == 0) {
			return false;
		}
		Long permissionId = permission.getId();
		// 检查大权限
		String resourceName = permission.getResource().split(":")[0] + ":ALL";
		// 检查小权限
		for (Permission userPermission : userPermissions) {
			System.out.println("有的权限" + userPermission.getResource());
			if (permissionId.equals(userPermission.getId())
					|| resourceName.equals(userPermission.getResource())) {
				System.out
						.println("++++++++++++++++++++++有权限++++++++++++++++++++++++++++");
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static List<Permission> getEmployeePermission() {
		return (List<Permission>) getRequest().getSession().getAttribute(
				PERMISSION_IN_SESSION);
	}

	public static void setEmployeePermission(List<Permission> permission) {
		getRequest().getSession().setAttribute(PERMISSION_IN_SESSION,
				permission);
	}

	public static String getRealPath() {
		return getRequest().getServletContext().getRealPath("/");
	}
	public static String getRealPathByClass() {
		String path = UserContext.class.getClassLoader().getResource("").getPath();
		return path.substring(1, path.length()-16);
	}
}
