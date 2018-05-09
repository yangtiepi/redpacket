package com.liuhe.redpacket.web.oms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuhe.redpacket.domain.SystemDictionaryItem;
import com.liuhe.redpacket.service.ISystemDictionaryItemService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.system.MethodAnnotation.ResourceType;
import com.liuhe.redpacket.utils.result.AjaxResult;

@Controller
@RequestMapping("/systemParamItem")
public class SystemParamItemController {
	@Autowired
	ISystemDictionaryItemService systemDictionaryItemService;

	@MethodAnnotation(name = "明细列表", type = ResourceType.系统参数)
	@ResponseBody
	@RequestMapping("/list")
	private List<SystemDictionaryItem> SystemDictionaryItemList(@RequestParam("id")Long id) {
		return systemDictionaryItemService.getByParent(id);
	}

	// 删除
	@MethodAnnotation(name = "明细删除", type = ResourceType.系统参数)
	@ResponseBody
	@RequestMapping("/delete")
	private AjaxResult delete(@RequestParam("id")Long id) {
		AjaxResult ar;
		try {
			if (id != null) {
				systemDictionaryItemService.delete(id);
				ar = new AjaxResult();
			}else {
				ar = new AjaxResult("删除失败", null);
			}
		} catch (Exception e) {
			ar = new AjaxResult("删除失败", null);
		}
		return ar;
	}

	// 保存和更新
	@MethodAnnotation(name = "明细编辑", type = ResourceType.系统参数)
	@ResponseBody
	@RequestMapping("/save")
	private AjaxResult save(SystemDictionaryItem systemDictionaryItem) {
		AjaxResult ar;
		try {
			// 判断是新建还是更新
			if (systemDictionaryItem != null
					&& systemDictionaryItem.getId() != null) {
				systemDictionaryItemService.update(systemDictionaryItem);
				ar = new AjaxResult("更新成功！");
			} else {
				systemDictionaryItemService.save(systemDictionaryItem);
				ar = new AjaxResult("保存成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ar = new AjaxResult("保存失败！", null);
		}
		return ar;
	}
}
