package com.monitoring.service;

import java.util.List;

import com.monitoring.entity.MonitorGroup;
import com.monitoring.entity.Monitoring;
import com.monitoring.util.Pager;

public interface MonitorgroupService {

	public int add(MonitorGroup monitorGroup);
	
	public int update(MonitorGroup monitorGroup);
	
	public int delete(int id);
	
	public MonitorGroup getMonitorGroupById(int id);
	 
	public Pager<MonitorGroup> getMonitorGroups(int pageNum);

	public List<MonitorGroup> getAllMonitorGroups();
}
