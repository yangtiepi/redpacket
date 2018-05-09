package com.liuhe.redpacket.service;

import java.util.Map;

public interface IWxService {

	/**
	 * 企业付款
	 * @param partnerTradeNo 商户订单号，需保持唯一性 (只能是字母或者数字，不能包含有符号)
	 * @param openid 用户的openid
	 * @param checkName 校验用户姓名选项, NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
	 * @param reUserName 用户真实姓名
	 * @param amount 企业付款金额
	 * @param desc 企业付款操作说明信息
	 * @param spbillCreateIp 调用接口的机器Ip地址
	 * @return
	 */
	public abstract Map<String,Object> weixinMmpaymkttransfers(
			String partnerTradeNo, String openid, String checkName,
			String reUserName, int amount, String desc, String spbillCreateIp);

}