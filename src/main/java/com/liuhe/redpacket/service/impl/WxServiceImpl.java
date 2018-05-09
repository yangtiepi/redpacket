package com.liuhe.redpacket.service.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuhe.redpacket.service.IWxService;
import com.liuhe.redpacket.utils.JaxbBinder;
import com.liuhe.redpacket.utils.Sha1Util;
import com.liuhe.redpacket.vo.weixin.pay.TransfersReq;
import com.liuhe.redpacket.vo.weixin.pay.TransfersRes;
import com.tencent.common.HttpsRequest;
import com.tencent.common.Signature;

@Service
@Transactional
public class WxServiceImpl implements IWxService {
	private Logger log = LoggerFactory.getLogger(WxServiceImpl.class);
	//公众账号ID
	@Value("${appid}")
	private String appid;
	//支付key
	@Value("${payKey}")
	private String payKey;
	//商户号
	@Value("${mchId}")
	private String mchId;
	//签名证书路径
	@Value("${certLocalPath}")
	private String certLocalPath;
	//签名证书密码
	@Value("${certPassword}")
	private String certPassword;
	

	
	/**
	 * 企业付款
	 * @param orders 订单
	 * @param partnerTradeNo 商户订单号，需保持唯一性 (只能是字母或者数字，不能包含有符号)
	 * @param openid 用户的openid
	 * @param checkName 校验用户姓名选项, NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
	 * @param reUserName 用户真实姓名
	 * @param amount 企业付款金额
	 * @param desc 企业付款操作说明信息
	 * @param spbillCreateIp 调用接口的机器Ip地址
	 * @return
	 */
	@Override
	public Map<String,Object> weixinMmpaymkttransfers(String partnerTradeNo, String openid, String checkName, String reUserName, int amount, String desc, String spbillCreateIp){
		Map<String, Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(partnerTradeNo)) {
			map.put("code", "1");
			map.put("msg", "partnerTradeNo商户订单号为空！");
			return map;
		}
		if (StringUtils.isBlank(openid)) {
			map.put("code", "1");
			map.put("msg", "openid为空！");
			return map;
		}
		if (StringUtils.isBlank(checkName)) {
			map.put("code", "1");
			map.put("msg", "checkName为空！");
			return map;
		}
		if (!"NO_CHECK".equals(checkName) && !"FORCE_CHECK".equals(checkName)) {
			map.put("code", "1");
			map.put("msg", "checkName参数无效！！");
			return map;
		}
		if ("FORCE_CHECK".equals(checkName)) {
			if (StringUtils.isBlank(reUserName)) {
				map.put("code", "1");
				map.put("msg", "reUserName用户真实姓名为空");
				return map;
			}
		}
		if (amount <= 0) {
			map.put("code", "1");
			map.put("msg", "amount付款金额不能小于等于0！");
			return map;
		}
		if (StringUtils.isBlank(desc)) {
			map.put("code", "1");
			map.put("msg", "desc付款说明为空！");
			return map;
		}
		if (StringUtils.isBlank(spbillCreateIp)) {
			map.put("code", "1");
			map.put("msg", "spbillCreateIp机器Ip地址为空！");
			return map;
		}
		
		TransfersReq req = new TransfersReq();
		req.setAppid(getAppid());
		req.setMchId(getMchId());
		req.setDeviceInfo(null);
		String nonceStr = Sha1Util.getNonceStr();
		req.setNonceStr(nonceStr);
		req.setPartnerTradeNo(partnerTradeNo);
		req.setOpenid(openid);
		req.setCheckName(checkName);
		req.setReUserName(reUserName);
		req.setAmount(amount * 100);
		req.setDesc(desc);
		req.setSpbillCreateIp(spbillCreateIp);
		
		JaxbBinder jb = new JaxbBinder(TransfersReq.class);
		String xml = jb.toXml(req, "UTF-8",true);
		String sign = null;
		try {
			log.info("sign:001");
			sign = Signature.getSignFromResponseString(xml, getPayKey());
			log.info("sign:002");
		} catch (Exception e) {
			log.error("获取微信接口请求签名异常：", e);
		}
		req.setSign(sign);
		
		String xmlString = jb.toXml(req, "UTF-8", true);
		log.info("req:"+ xmlString);
		try {
			String res = "";
			HttpsRequest request = new HttpsRequest(getCertLocalPath(), getCertPassword());
			log.info("req:01");
			res = request.sendPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers", xmlString, getCertLocalPath(), getCertPassword());
			log.info("res:"+ res);
			
			JaxbBinder resjb =new JaxbBinder(TransfersRes.class);
			TransfersRes transfersRes = resjb.fromXml(res);
			
			// 通信正常
			if ("SUCCESS".equals(transfersRes.getReturnCode())) {
				if ("SUCCESS".equals(transfersRes.getResultCode())) {
					// 交易成功
					map.put("code", "0");
					map.put("msg", "交易成功！");
					return map;
				} else {
					// 系统错误，请重试
					if ("SYSTEMERROR".equals(transfersRes.getErrCode())) {
						
					}
					log.info("[微信企业付款错误描述]"+ transfersRes.getErrCodeDes());
					map.put("code", "1");
					map.put("msg", transfersRes.getErrCodeDes());
					return map;
				}
			} else {
				log.info("[微信企业付款返回信息]"+ transfersRes.getReturnMsg());
				map.put("code", "1");
				map.put("msg", transfersRes.getReturnMsg());
				return map;
			}
		} catch (UnrecoverableKeyException e) {
			log.error("req:0005", e);
		} catch (KeyManagementException e) {
			log.error("req:0004", e);
		} catch (KeyStoreException e) {
			log.error("req:0003", e);
		} catch (NoSuchAlgorithmException e) {
			log.error("req:0002", e);
		} catch (IOException e) {
			log.error("req:0001", e);
		} finally{
			log.info("req:0000");
		}
		return null;
	}
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPayKey() {
		return payKey;
	}
	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getCertLocalPath() {
		return certLocalPath;
	}
	public void setCertLocalPath(String certLocalPath) {
		this.certLocalPath = certLocalPath;
	}
	public String getCertPassword() {
		return certPassword;
	}
	public void setCertPassword(String certPassword) {
		this.certPassword = certPassword;
	}
	
}
