//package com.liuhe.redpacket.web.app.mobilePage;
//
///**
// * 客户相关的接口
// */
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.liuhe.redpacket.domain.Customer;
//import com.liuhe.redpacket.service.ICustomerService;
//import com.liuhe.redpacket.utils.result.AppResult;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//
//@Controller
//@RequestMapping("/mobilePage/customerPage")
//public class CustomerPage {
//	@Autowired
//	private ICustomerService customerService;
//	
//	/**
//	 * 下载客户列表
//	 * @return
//	 */
//	@RequestMapping("/downloadCustomerList")
//	@ResponseBody
//	@ApiOperation(value = "获取客户列表（含欠款信息）", httpMethod = "POST", response = AppResult.class, notes = "获取客户列表（含欠款信息）")
//	public AppResult downloadCustomerList(
//			@ApiParam(required = true, name = "storeId", value = "店铺id")
//			@RequestParam("storeId") String storeId,
//			@ApiParam(name = "customerName", value = "客户名字，用于搜索客户,查询全部传空")
//			@RequestParam("customerName") String customerName) {
//		AppResult appResult;
//		try {
//			List<Customer> customerList= customerService.findCustomer_Debit(storeId, customerName);
//			appResult = new AppResult(customerList);
//		} catch (Exception e) {
//			e.printStackTrace();
//			appResult = new AppResult(e.getMessage(), null);
//		}
//		return appResult;
//	}
//
//}
