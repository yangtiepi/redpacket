package com.liuhe.redpacket.web.oms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class _TestCotroller {

	/**
	 * 测试主页
	 * @return
	 */
	@RequestMapping("/index")
	public String adPositionId() {
		System.out.println(111111);
		return "forward:/WEB-INF/test/upload.jsp";
	}

}
