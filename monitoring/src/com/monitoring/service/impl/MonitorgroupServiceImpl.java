package com.monitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitoring.dao.MonitorgroupDao;
import com.monitoring.entity.MonitorGroup;
import com.monitoring.service.MonitorgroupService;
import com.monitoring.util.Pager;

@Service
public class MonitorgroupServiceImpl implements MonitorgroupService {

	@Autowired
	MonitorgroupDao monitorgroupDao;

	@Override
	public int add(MonitorGroup monitorGroup) {
		return monitorgroupDao.add(monitorGroup);
	}

	@Override
	public int update(MonitorGroup monitorGroup) {

		return monitorgroupDao.update(monitorGroup);
	}

	@Override
	public int delete(int id) {
		return monitorgroupDao.delete(id);
	}

	@Override
	public MonitorGroup getMonitorGroupById(int id) {
		return monitorgroupDao.getMonitorGroupById(id);
	}

	@Override
	public Pager<MonitorGroup> getMonitorGroups(int pageNum) {
		List<MonitorGroup> list = monitorgroupDao.getMonitorGroups();
		Pager<MonitorGroup> pager = new Pager<MonitorGroup>(pageNum,2,list);
		return pager;
	}

	@Override
	public List<MonitorGroup> getAllMonitorGroups() {
		return monitorgroupDao.getMonitorGroups();
	}

}
