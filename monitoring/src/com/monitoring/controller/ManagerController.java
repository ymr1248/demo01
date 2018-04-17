package com.monitoring.controller;

import com.monitoring.entity.Manager;
import com.monitoring.service.ManagerService;
import com.monitoring.service.UserGradeService;
import com.monitoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SessionAttributes(value = "manager")
@Controller
public class ManagerController {
    @Autowired
    UserService userService;
    @Autowired
    UserGradeService userGradeService;
    @Autowired
    ManagerService managerService;

    /**
     *  用户登录
     *  输入账号密码
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/managerLogin")
    public String managerLogin(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String path = request.getContextPath();
        String account = request.getParameter("managerAccount");
        String password = request.getParameter("password");
        int isLogin = managerService.managerLogin(account,password,path);
        if(isLogin == 1){
        	Manager manager = managerService.getManagerByAccount(account);
        	request.getSession().setAttribute("MANAGER", manager);
            return "index";
        }else if(isLogin == 0){
            //密码错误
            modelMap.put("reason", "密码错误，请检查您的密码是否正确？");
            return "page_400";
        }else{
            //账号错误
            modelMap.put("reason", "账号错误，请检查您的账号是否正确？");
            return "page_400";
        }

    }

}
