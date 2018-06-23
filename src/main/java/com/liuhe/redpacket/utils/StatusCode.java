package com.liuhe.redpacket.utils;
/**
 * 状态码
 * @author ozil
 *
 */
public class StatusCode {
	//=============================账户相关==================================
	/**
	 * 用户名错误
	 */
	public static final Integer USERNAME_ERROR = -100;
	/**
	 * 密码错误
	 */
	public static final Integer PASSWORD_ERROR = -101;
	/**
	 * 验证码错误
	 */
	public static final Integer RANDOM_ERROR = -102;
	/**
	 * 账号状态异常,已停用！！
	 */
	public static final Integer STATE_ERROR = -103;
	/**
	 * 用户已存在
	 */
	public static final Integer USERNAME_EXISTING = -104;
	/**
	 * 收货地址不存在
	 */
	public static final Integer ADDRESS_NOTFOUND = -105;
	/**
	 * 邀请码无效
	 */
	public static final Integer USER_USECODE_INVALID = -106;
	/**
	 * 账户未停用,不能删除
	 */
	public static final Integer USER_NOTDELETE = -107;
	/**
	 * 该微信号还未绑定任何账户
	 */
	public static final Integer USER_NOWECHAT = -108;
	/**
	 * 不能修改管理员账户
	 */
	public static final Integer USER_NOTEDIT_ADMIN = -109;
	/**
	 * 账户余额不足
	 */
	public static final Integer ACCOUNT_NO_BALANCE = -110;

}
