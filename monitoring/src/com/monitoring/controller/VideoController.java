package com.monitoring.controller;

import java.util.Date;
import java.util.List;

import javax.mail.search.StringTerm;
import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monitoring.entity.Monitoring;
import com.monitoring.entity.User;
import com.monitoring.entity.Video;
import com.monitoring.service.MonitoringService;
import com.monitoring.service.UserService;
import com.monitoring.service.VideoService;
import com.monitoring.util.MyTestUtils;
import com.monitoring.util.Pager;
import com.monitoring.util.TimeUtil;

@Controller
public class VideoController {

	@Autowired
	VideoService videoService;
	@Autowired 
	UserService userService;
	@Autowired
	MonitoringService monitoringService;
	
	
	@RequestMapping(value="/toVideo_add")
	public String toVideo_add(HttpServletRequest request,ModelMap modelMap){
		if (MyTestUtils.isLogined(request) != null) {
			return "page_403";
		}
		List<User> list1 = userService.getUsers();
		List<Monitoring> list2 = monitoringService.getMonitorings();
		modelMap.addAttribute("userList", list1);
		modelMap.addAttribute("monitoringList", list2);	
		return "video_add";
	}
	
	@RequestMapping(value="/video_add")
	public String video_add(Video video,HttpServletRequest request,ModelMap modelMap){
		if (MyTestUtils.isLogined(request) != null) {
			return "page_403";
		}
		Date videoStart = TimeUtil.stringToDate(request.getParameter("videoStart1"));
		Date videoEnd = TimeUtil.stringToDate(request.getParameter("videoEnd1"));
		User user = userService.getUserById(Integer.parseInt(request.getParameter("userId1")));
		Monitoring monitoring = monitoringService.getMonitoringById(Integer.parseInt(request.getParameter("MonitoringId1")));
		video.setUser(user);
		video.setMonitoring(monitoring);
		video.setVideoStart(videoStart);
		video.setVideoEnd(videoEnd);
		video.setVideoDate(new Date());
		videoService.add(video);
		return "index";
	}
	
	@RequestMapping(value="/toVideo_list")
	public String toVideo_list(HttpServletRequest request,ModelMap modelMap){
		if (MyTestUtils.isLogined(request) != null) {
			return "page_403";
		}
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int monitoringId = 0;
		int flag = 0;
		Date startTime = null;
		Date endTime = null;
		User user = userService.getUserById(userId);
		Monitoring monitoring = null;
		if(request.getParameter("monitoringId")!=null){
			monitoringId = Integer.parseInt(request.getParameter("monitoringId"));
			monitoring = monitoringService.getMonitoringById(monitoringId);
			flag=1;
		}
		if(request.getParameter("startTime")!=null){
			startTime = TimeUtil.stringToDate(request.getParameter("startTime"));
			endTime = TimeUtil.stringToDate(request.getParameter("endTime"));
			flag=2;
		}
		if(flag==0){
			Pager<Video> pager = videoService.getVideosByPageNum(videoService.getVideos(user), pageNum);
			modelMap.addAttribute("videoList", pager.getDataList());
			modelMap.addAttribute("totalPage", pager.getTotalPage());
			modelMap.addAttribute("pageNum", pageNum);
			return "video_list";
		}else if (flag==1) {
			Pager<Video> pager = videoService.getVideosByPageNum(videoService.getVideos(user,monitoring), pageNum);
			modelMap.addAttribute("videoList", pager.getDataList());
			modelMap.addAttribute("totalPage", pager.getTotalPage());
			modelMap.addAttribute("pageNum", pageNum);
			return "video_list";
		}else {
			Pager<Video> pager = videoService.getVideosByPageNum(videoService.getVideos(user,monitoring,startTime,endTime), pageNum);
			modelMap.addAttribute("videoList", pager.getDataList());
			modelMap.addAttribute("totalPage", pager.getTotalPage());
			modelMap.addAttribute("pageNum", pageNum);
			return "video_list";
		}
	} 
	
	
	
	
}
