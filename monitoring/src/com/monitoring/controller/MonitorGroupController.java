package com.monitoring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.monitoring.entity.MonitorGroup;
import com.monitoring.service.MonitorgroupService;
import com.monitoring.util.MyTestUtils;

@Controller
public class MonitorGroupController {

	@Autowired
	MonitorgroupService monitorgroupService;
	
	/*
	 * 前往添加机器组页面
	 */
	@RequestMapping(value = "/toMonitorgroup_add")
	public String toMonitorgroup_add(HttpServletRequest request, ModelMap modelMap) {
		if (MyTestUtils.userIsLogined(request) != null) {
			return "page_403";
		}
		return "userviews/monitorgroup_add";
	}

	/*
	 * 添加机器组
	 */
	@RequestMapping(value = "/monitorgroup_add")
	public String monitorgroup_add(MonitorGroup monitorGroup, HttpServletRequest request, ModelMap modelMap) {
		if (MyTestUtils.userIsLogined(request) != null) {
			return "page_403";
		}
		if(monitorgroupService.add(monitorGroup)!=1){
			modelMap.put("reason", "添加失败！！");
			return "page_400";
		}
		return "userviews/user_index";
	}
	
	/*
	 * 通过页码查询数据
	 */
	@RequestMapping(value = "/toMonitorgroup_list")
	public String toMonitorgroup_list(int pageNum, HttpServletRequest request, ModelMap modelMap) {
		if (MyTestUtils.userIsLogined(request) != null) {
			return "page_403";
		}
		modelMap.addAttribute("list", monitorgroupService.getMonitorGroups(pageNum).getDataList());
		modelMap.addAttribute("pageNum",pageNum);
		modelMap.addAttribute("totalPage", monitorgroupService.getMonitorGroups(pageNum).getTotalPage());
		return "userviews/monitorgroup_list";
	}
	
	/*
	 * 删除数据
	 */
	@RequestMapping(value = "/monitorgroup_delete")
	public String monitorgroup_delete(int pageNum,int id, HttpServletRequest request, ModelMap modelMap) {
		if (MyTestUtils.userIsLogined(request) != null) {
			return "page_403";
		}
		if(monitorgroupService.delete(id)!=1){
			modelMap.put("reason", "删除失败！！");
			return "page_400";
		}
		modelMap.addAttribute("list", monitorgroupService.getMonitorGroups(pageNum).getDataList());
		modelMap.addAttribute("pageNum",pageNum);
		modelMap.addAttribute("totalPage", monitorgroupService.getMonitorGroups(pageNum).getTotalPage());
		return "userviews/monitorgroup_list";
	}	
	
	/*
	 * 前往更新页面
	 */
	@RequestMapping(value = "/toMonitorgroup_update")
	public String toMonitorgroup_update(int id, HttpServletRequest request, ModelMap modelMap) {
		if (MyTestUtils.userIsLogined(request) != null) {
			return "page_403";
		}
		modelMap.addAttribute("list", monitorgroupService.getMonitorGroupById(id));
		return "userviews/monitorgroup_update";
	}
	
	/*
	 * 更新数据
	 */
	@RequestMapping(value = "/monitorgroup_update")
	public String monitorgroup_update(MonitorGroup monitorGroup, HttpServletRequest request, ModelMap modelMap) {
		if (MyTestUtils.userIsLogined(request) != null) {
			return "page_403";
		}
		if(monitorgroupService.update(monitorGroup)!=1){
			modelMap.put("reason", "删除失败！！");
			return "page_400";
		}
		return "redirect:toMonitorgroup_list?pageNum=1";
	}
	
	
}
