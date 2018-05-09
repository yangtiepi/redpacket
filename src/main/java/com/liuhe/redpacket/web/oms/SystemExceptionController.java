package com.liuhe.redpacket.web.oms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liuhe.redpacket.domain.SystemException;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.SystemExceptionQuery;
import com.liuhe.redpacket.service.ISystemExceptionService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;

@Controller
@RequestMapping("/systemException")
public class SystemExceptionController {
	@Autowired
	private ISystemExceptionService systemExceptionService;

	@MethodAnnotation(name = "主页", type = ResourceType.系统异常)
	@RequestMapping("/index")
	private String index() {
		return "systemLog/systemException";
	}

	@MethodAnnotation(name = "查询", type = ResourceType.系统异常)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<SystemException> query(SystemExceptionQuery q) {
		return systemExceptionService.query(q);
	}
	/**
	 * 文章内容
	 * @return
	 */
	@RequestMapping("/showException")
	private ModelAndView showArticle(Long id) {
		SystemException systemException = systemExceptionService.get(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("detail", systemException.getExceptionDetail());
		modelAndView.setViewName("/systemLog/exceptionDetail");
		return modelAndView;
	}
}
