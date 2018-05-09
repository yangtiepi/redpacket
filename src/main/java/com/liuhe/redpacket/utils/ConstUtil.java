package com.liuhe.redpacket.utils;

import java.math.BigDecimal;


/**
 * 常量
 * @author ozil
 *
 */
public class ConstUtil {
	// ===========================系统storeId=============================
	public static final Long SYSTEM_STORE_ID = 0L;
	// ===========================系统storeId=============================
	public static final Long SYSTEM_EMPLOYEE_ID = 0L;
	// ===========================超级管理员id=============================
	public static final Long ADMIN_ID = 1L;
//	
//	
//	// =======================金额============================
	/**
	 * 负一 用于取反
	 */
	public static final BigDecimal NEGATIVE = new BigDecimal("-1");
	/**
	 * 零
	 */
	public static final BigDecimal ZERO = new BigDecimal("0.00");

	/**
	 * 一百
	 */
	public static final BigDecimal HUNDRED = new BigDecimal("100.00");
//
	// ===========================员工状态=================================
	/**
	 * 停用(离职)
	 */
	public final static int EMPLOYEE_LEAVE = 0;
	/**
	 * 正常
	 */
	public final static int EMPLOYEE_NORMAL = 1;
//	

//	//==========================系统参数key==================================
	/**
	 * 用户可领取红包个数
	 */
	public static final String USER_REDPACKET_DAY = "userRedPacketDay";
	public static final String USER_REDPACKET_WEEK = "userRedPacketWeek";
	public static final String USER_REDPACKET_MONTH = "userRedPacketMonth";
	
	// ===========================用户状态=================================
	/**
	 * 停用
	 */
	public final static int USER_LEAVE = 0;
	/**
	 * 正常
	 */
	public final static int USER_NORMAL = 1;
	// ===========================集卡状态=================================
	/**
	 * 停用
	 */
	public final static int CARDS_DISABLED = 0;
	/**
	 * 正常
	 */
	public final static int CARDS_ENABLED = 1;
	// ===========================红包状态=================================
	/**
	 * 停用
	 */
	public final static int REDPACKET_DISABLED = 0;
	/**
	 * 正常
	 */
	public final static int REDPACKET_ENABLED = 1;
	// ===========================首页状态=================================
	/**
	 * 停用
	 */
	public final static int HOMEPAGE_DISABLED = 0;
	/**
	 * 正常
	 */
	public final static int HOMEPAGE_ENABLED = 1;
	// ===========================广告状态=================================
	/**
	 * 停用
	 */
	public final static int AD_DISABLED = 0;
	/**
	 * 正常
	 */
	public final static int AD_ENABLED = 1;
	// ===========================集卡领取状态=================================
	/**
	 * 未领取
	 */
	public final static int CARDS_COMPLETE = 0;
	/**
	 * 已领取
	 */
	public final static int CARDS_RECEIVE = 1;

}
