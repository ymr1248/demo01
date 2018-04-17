package com.monitoring.service.impl;

import java.util.List;

import com.monitoring.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitoring.dao.MonitoringDao;
import com.monitoring.entity.Monitoring;
import com.monitoring.service.MonitoringService;

/**
 * @author mry
 *2018年1月2日 上午11:21:12
 */
@Service
public class MonitoringServiceImpl implements MonitoringService {

	@Autowired
	MonitoringDao monitoringDao;
	@Override
	public int updateOrsave(Monitoring monitoring) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delMonitoring(int id) {
		// TODO Auto-generated method stub
		if(monitoringDao.delMonitoring(id) == 1){
			return true;
		}
		return false;
	}

	@Override
	public int add(Monitoring monitoring) {
		return monitoringDao.add(monitoring);
	}

	@Override
	public Monitoring getMonitoringById(int id) {
		// TODO Auto-generated method stub
		return monitoringDao.getMonitoringById(id);
	}

	@Override
	public List<Monitoring> getMonitorings() {
		// TODO Auto-generated method stub
		return monitoringDao.getMonitorings();
	}

	@Override
	public Pager<Monitoring> getMonitorByUserId(int pageNum,int userId) {
		List<Monitoring> monitorings = monitoringDao.getMonitorByUserId(userId);
		Pager<Monitoring> pager = new Pager<>(pageNum,10,monitorings);
		return pager;
	}

	@Override
	public boolean updateMonitorByAddition(Monitoring monitoring) {
		if(monitoringDao.updateMonitorByAddition(monitoring) == 1){
			return true;
		}
		return false;
	}

	@Override
	public List<Monitoring> getMonitoringsByUserId(int userId) {
		return monitoringDao.getMonitorByUserId(userId);
	}

}
