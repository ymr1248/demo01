package com.monitoring.controller;

import com.monitoring.entity.MonitorGroup;
import com.monitoring.entity.Monitoring;
import com.monitoring.service.MonitorgroupService;
import com.monitoring.service.MonitoringService;
import com.monitoring.service.UserService;
import com.monitoring.util.MyTestUtils;
import com.monitoring.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.monitor.Monitor;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class MonitoringController {
    @Autowired
    UserService userService;
    @Autowired
    MonitoringService monitoringService;
    @Autowired
    MonitorgroupService monitorgroupService;

    /**
     * 通过用户ID查找监控器并返回
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/toShowMonitorJSP")
    public String toShowMonitorJSP(HttpServletRequest request, ModelMap modelMap) {
        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));
            modelMap.addAttribute("list", monitoringService.getMonitorByUserId(pageNum, userId));
            modelMap.addAttribute("pageNum", pageNum);
            modelMap.addAttribute("pageNums", monitoringService.getMonitorByUserId(pageNum, userId).getTotalPage());

            return "user_monitor_list";
        }
    }

    /**
     * 跳转到添加监控器
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/toAddMonitor")
    public String toAddMonitor(HttpServletRequest request, ModelMap modelMap) {
        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            return "add_monitor";
        }
    }

    /**
     * 添加监控器
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/addMonitor")
    public String addMonitor(HttpServletRequest request, ModelMap modelMap) {
        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            HashMap<String,String> map = new HashMap<>();
            Monitoring monitoring = new Monitoring();

            String monitorName = request.getParameter("monitorName");
            String monitorIp = request.getParameter("monitorIp");
            String monitorLocation = request.getParameter("monitorLocation");
            String monitorType = request.getParameter("monitorType");
            int userId = Integer.parseInt(request.getParameter("userId"));
            int monitorId = Integer.parseInt(request.getParameter("monitorId"));
            Date locatedTime = new Date();
            int monitorStatus = Integer.parseInt(request.getParameter("monitorStatus"));
            String monitorInfo = request.getParameter("monitorInfo");
            String storageLocation = request.getParameter("storageLocation");

            monitoring.setMonitorIp(monitorIp);
            monitoring.setMonitorGroup(monitorgroupService.getMonitorGroupById(monitorId));
            monitoring.setMonitorLocation(monitorLocation);
            monitoring.setMonitorName(monitorName);
            monitoring.setMonitorInfo(monitorInfo);
            monitoring.setLocatedTime(locatedTime);
            monitoring.setMonitorType(monitorType);
            monitoring.setUser(userService.getUserById(userId));
            monitoring.setMonitorStatus(monitorStatus);
            monitoring.setStorageLocation(storageLocation);
            if (monitoringService.add(monitoring) != 1) {
                modelMap.put("reason", "添加失败！！");
                return "page_400";
            }
            return "index";
        }

    }

    /**
     * 用户删除名下监控器
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/deleteMonitor")
    public String deleteMonitor(HttpServletRequest request, ModelMap modelMap) {
        if (MyTestUtils.isLogined(request) != null) {
            return "page_403";
        } else {
            int monitorId = Integer.parseInt(request.getParameter("monitorId"));
            if(!monitoringService.delMonitoring(monitorId)){
                modelMap.put("reason","删除失败请重试！！！");
                return "page_400";
            }
            return "index";
        }
    }

    @RequestMapping(value = "/toUpdateMonitor")
    public String toUpdateMonitor(HttpServletRequest request,ModelMap modelMap){
        if(MyTestUtils.isLogined(request) != null){
            return"page_403";
        }else{
            int monitorId = Integer.parseInt(request.getParameter("monitorId"));
            List<MonitorGroup> monitorGroups = monitorgroupService.getAllMonitorGroups();
            Monitoring monitor = monitoringService.getMonitoringById(monitorId);
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));
            modelMap.addAttribute("monitor",monitor);
            modelMap.addAttribute("monitorGroups",monitorGroups);
            modelMap.addAttribute("pageNum",pageNum);
            return "update_monitor";
        }
    }

    @RequestMapping(value = "/updateMonitor")
    public String updateMonitor(HttpServletRequest request,ModelMap modelMap){
        if(MyTestUtils.isLogined(request) != null){
            return"page_403";
        }else {
            Monitoring monitoring = new Monitoring();
            String monitorId = request.getParameter("monitorId");
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));

            String monitorIp = request.getParameter("monitorIp");
            String monitorName = request.getParameter("monitorName");
            String monitorInfo = request.getParameter("monitorInfo");
            String monitorLocation = request.getParameter("monitorLocation");
            int monitorStatus  = Integer.parseInt(request.getParameter("monitorStatus"));
            int groupId = Integer.parseInt(request.getParameter("groupId"));
            String storageLocation = request.getParameter("storageLocation");

            monitoring.setMonitorIp(monitorIp);
            monitoring.setMonitorName(monitorName);
            monitoring.setMonitorInfo(monitorInfo);
            monitoring.setMonitorLocation(monitorLocation);
            monitoring.setMonitorStatus(monitorStatus);
            monitoring.setStorageLocation(storageLocation);
            monitoring.setMonitorGroup(monitorgroupService.getMonitorGroupById(groupId));

            if (monitoringService.updateMonitorByAddition(monitoring)){
                int userId = Integer.parseInt(request.getParameter("userId"));
                modelMap.addAttribute("list", monitoringService.getMonitorByUserId(pageNum, userId));
                modelMap.addAttribute("pageNum", pageNum);
                modelMap.addAttribute("pageNums", monitoringService.getMonitorByUserId(pageNum, userId).getTotalPage());

                return "user_monitor_list";
            }else{
                modelMap.put("reason","跟新出错请重新输入！！！");
                return "page_400";
            }
        }
    }


}
