/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.web.oms;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.Redpacket;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.RedpacketQuery;
import com.liuhe.redpacket.service.IRedpacketService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
import com.liuhe.redpacket.utils.result.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.liuhe.redpacket.utils.UserContext.getResponse;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/redpacket")
public class RedpacketController{

	@Autowired
	IRedpacketService redpacketService;

	/**
	 * 主页
	 * @return
	 */
	@MethodAnnotation(name = "主页", type = ResourceType.红包)
	@RequestMapping("/index")
	private String Redpacket() {
		return "redpacket/redpacket";
	}
	/**
	 * 红包列表
	 * @return
	 */
	@MethodAnnotation(name = "列表", type = ResourceType.红包)
	@RequestMapping("/list")
	@ResponseBody
	private List<Redpacket> showRedpacket() {
		List<Redpacket> list = redpacketService.getAll();
		return list;
	}

	/**
	 * 高级查询+分页
	 * 
	 * @param qu
	 * @return
	 */
	@MethodAnnotation(name = "查询", type = ResourceType.红包)
	@RequestMapping("/query")
	@ResponseBody
	private PageResult<Redpacket> query(RedpacketQuery qu) {
		PageResult<Redpacket> list = redpacketService.query(qu);
		return list;
	}

	/**
	 * 添加
	 * 
	 * @param redpacket
	 * @return
	 */
	@MethodAnnotation(name = "保存", type = ResourceType.红包)
	@RequestMapping("/save")
	@ResponseBody
	private AjaxResult save(Redpacket redpacket) {
		AjaxResult ar;
		try {
			if (redpacket!=null && redpacket.getId()!=null) {
				redpacketService.update(redpacket);
			}else {
				redpacketService.save(redpacket);
			}
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@MethodAnnotation(name = "删除", type = ResourceType.红包)
	@RequestMapping("/delete")
	@ResponseBody
	private AjaxResult delete(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			redpacketService.delete(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}
	
	/**
	 * 停用
	 * @return
	 */
	@MethodAnnotation(name = "停用", type = ResourceType.红包)
	@RequestMapping("/disable")
	@ResponseBody
	private AjaxResult disable(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			redpacketService.disable(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}
	
	/**
	 * 启用
	 * @return
	 */
	@MethodAnnotation(name = "启用", type = ResourceType.红包)
	@RequestMapping("/enable")
	@ResponseBody
	private AjaxResult enable(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			redpacketService.enable(id);
			ar = new AjaxResult();
		} catch (LogicException e) {
			ar = new AjaxResult(e.getMessage(), e.getErrorCode());
		}
		return ar;
	}


	/**
	 * 下载批量导入模板
	 *
	 * @param request
	 * @param response
	 *
	 * @throws Exception
	 */
	@RequestMapping("/downloadTemplet")
	public void downloadTemplet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("");
		String code = request.getParameter("code");

		File file = new File(path + File.separator + "template" + File.separator + "bacth_redpacket.csv");
		if (file.exists()) {
			response.setContentType("application/csv;charset=GBK");
			response.setHeader("Content-Disposition", "attachment; filename=bacth_redpacket.csv");
			OutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(file);
			int len = 0;
			byte[] b = new byte[1024];
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
			out.close();
			in.close();
		}

	}
}
