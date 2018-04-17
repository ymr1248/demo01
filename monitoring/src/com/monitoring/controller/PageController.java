/**
 * 
 */
package com.monitoring.controller;

import com.monitoring.service.ManagerService;
import com.monitoring.util.MyTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Lee 2017年12月18日
 *
 */
@Controller
public class PageController {
	@Autowired
	ManagerService managerService;
	@RequestMapping(value = "/page_403")
	public String page_403(HttpServletRequest request, ModelMap modelMap) {
		return "page_403";
	}

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, ModelMap modelMap) {
		if (MyTestUtils.isLogined(request)!=null) {
			return "page_403";
		}else {
			return "index";
		}
	}


	@RequestMapping(value = "/userIndex")
	public String userIndex(HttpServletRequest request, ModelMap modelMap){
		if (MyTestUtils.userIsLogined(request)!=null) {
			return "page_403";
		}else {
			return "userviews/user_index";
		}
	}


	@RequestMapping(value = "/")
	public String index1(HttpServletRequest request, ModelMap modelMap) {
		if (MyTestUtils.isLogined(request)!=null) {
			return "page_403";
		}else {
			return "index";
		}
	}

	@RequestMapping(value = "/page_400")
	public String page_400(HttpServletRequest request, ModelMap modelMap) {
		return "page_400";
	}

	@RequestMapping(value = "/page_404")
	public String page_404(HttpServletRequest request, ModelMap modelMap) {
		return "page_404";
	}

	@RequestMapping(value = "/page_500")
	public String page_500(HttpServletRequest request, ModelMap modelMap) {
		return "page_500";
	}

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, ModelMap modelMap) {
		return "login";
	}

	@RequestMapping(value ="/userlogin")
	public String userLogin(HttpServletRequest request, ModelMap modelMap) {
		return "userviews/user_login";
	}
}
