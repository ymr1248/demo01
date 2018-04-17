package com.monitoring.dao;

import java.util.List;

import com.monitoring.entity.MonitorGroup;

public interface MonitorgroupDao {

	public int add(MonitorGroup monitorGroup);

	public int update(MonitorGroup monitorGroup);

	public int delete(int id);

	public MonitorGroup getMonitorGroupById(int id);

	public List<MonitorGroup> getMonitorGroups();

}
