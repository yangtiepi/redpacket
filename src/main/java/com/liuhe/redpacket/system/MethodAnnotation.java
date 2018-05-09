package com.liuhe.redpacket.system;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个在方法上的自定义注解
 * @author ozil
 *
 */
/**
 * 定义一个在方法上的自定义注解
 * @author ozil
 *
 */
/**
 * 定义一个在方法上的自定义注解
 * @author ozil
 *
 */
/**
 * 定义一个在方法上的自定义注解
 * 
 * @author ozil
 *
 */
@Target(ElementType.METHOD)
// 加在方法上的注解
@Retention(RetentionPolicy.RUNTIME)
// 在运行时保存注解
public @interface MethodAnnotation {
	/**
	 * 资源类别枚举
	 */
	public enum ResourceType {
		系统权限, 系统账号, 系统账户, 系统日志, 系统异常, 系统监控, 用户账号,  系统参数,  广告主, 卡片, 广告点击记录, 卡集, 集卡兑换, 首页, 红包, 红包统计, 用户卡片
	};

	String name();// 资源名字

	ResourceType type();// 资源类型
}