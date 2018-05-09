//package com.liuhe.redpacket.web.app.base;
//
///**
// * 客户相关的接口
// */
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.liuhe.redpacket.domain.Customer;
//import com.liuhe.redpacket.service.ICustomerService;
//import com.liuhe.redpacket.utils.result.AppResult;
//import com.liuhe.redpacket.web.app.base.paramList.CustomerList;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//
//@Controller
//@RequestMapping("/app/customer")
//public class CustomerAPP {
//	@Autowired
//	private ICustomerService customerService;
//
//	/**
//	 * 上传客户列表
//	 * @return
//	 */
//	@RequestMapping("/uploadCustomerList")
//	@ResponseBody
//	@ApiOperation(value = "上传客户列表", httpMethod = "POST", response = AppResult.class, notes = "上传修改或者新增的客户列表数据")
//	public AppResult uploadCustomerList(@RequestBody
//			@ApiParam(required = true, name = "customerList", value = "客户列表（新增客户updateTime为空）")
//			CustomerList customerList) {
//		AppResult appResult;
//		try {
//			customerService.uploadCustomerList(customerList.getCustomerList());
//			appResult = new AppResult(null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			appResult = new AppResult(e.getMessage(), null);
//		}
//		return appResult;
//	}
//	
//	/**
//	 * 下载客户列表
//	 * @return
//	 */
//	@RequestMapping("/downloadCustomerList")
//	@ResponseBody
//	@ApiOperation(value = "下载客户列表", httpMethod = "POST", response = AppResult.class, notes = "下载修改或者新增的客户列表数据")
//	public AppResult downloadCustomerList(
//			@ApiParam(required = true, name = "storeId", value = "店铺id")
//			@RequestParam("storeId") String storeId,
//			@ApiParam(required = true, name = "updateTime", value = "最后更新时间:yyyy-dd-MM HH:mm:ss")
//			@RequestParam("updateTime") String updateTime) {
//		AppResult appResult;
//		try {
//			List<Customer> customerList= customerService.downloadCustomerList(storeId, updateTime);
//			appResult = new AppResult(customerList);
//		} catch (Exception e) {
//			e.printStackTrace();
//			appResult = new AppResult(e.getMessage(), null);
//		}
//		return appResult;
//	}
//
//}
