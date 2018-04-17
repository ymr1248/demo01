package com.monitoring.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.spi.CreationalContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.monitoring.dao.VideoDao;
import com.monitoring.entity.Monitoring;
import com.monitoring.entity.User;
import com.monitoring.entity.Video;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * @author mry 2018年1月2日 上午11:23:29
 */
@Repository
public class VideoDaoImpl implements VideoDao {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.openSession();
	}

	@Override
	public int add(Video video) {
		Session session = this.getSession();
		try {
			session.save(video);
		} finally {
			session.close();
		}
		return 1;
	}

	@Override
	public int update(Video video) {
		Session session = this.getSession();
		try {
			session.update(video);
		} finally {
			session.close();
		}
		return 1;
	}

	@Override
	public int delVideo(int id) {
		Session session = this.getSession();
		int result;
		try {
			result = session.createQuery("delete Video from where id =?").setParameter(0, id).executeUpdate();
		} finally {
			session.close();
		}
		return result;
	}

	@Override
	public Video getVideoById(int id) {
		Session session = this.getSession();
		Video video;
		try {
			video = (Video) session.createQuery("Video from where id=?").setParameter(0, id).uniqueResult();
		} finally {
			session.close();
		}
		return video;
	}

	@Override
	public List<Video> getVideos(User user) {
		List<Video> list = new ArrayList<Video>();
		Session session = this.getSession();
		try {
			list = session.createQuery("Video from where userId=?").setParameter(0, user.getId()).list();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Video> getVideos(User user, Monitoring monitoring) {
		List<Video> list = new ArrayList<Video>();
		Session session = this.getSession();
		try {
			list = session.createQuery("Video from where userId=? and monitoringId=?").setParameter(0, user.getId()).setParameter(1, monitoring.getId()).list();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Video> getVideos(User user, Monitoring monitoring, Date startTime, Date endTime) {
		List<Video> list = new ArrayList<Video>();
		Session session = this.getSession();
		try {
			list = session.createQuery("Video from where userId=? and monitoringId=? and videoStart<=? and videoEnd>=?")
					.setParameter(0, user.getId()).setParameter(1, monitoring.getId()).setParameter(2, startTime).setParameter(3, endTime)
					.list();
		} finally {
			session.close();
		}
		return list;
	}

	
	
	
}
