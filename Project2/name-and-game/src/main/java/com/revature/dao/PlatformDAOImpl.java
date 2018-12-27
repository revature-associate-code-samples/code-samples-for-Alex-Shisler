package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.model.Game;
import com.revature.model.Genre;
import com.revature.model.Platform;
import com.revature.util.HibernateUtil;

public class PlatformDAOImpl implements PlatformDAO {

	@Override
	public Platform getPlatform(String name) {
		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM PLATFORM WHERE NAME = ?";
		Query<Platform> q = s.createNativeQuery(sql, Platform.class);
		q.setParameter(1, name);
		Platform platform = q.uniqueResult();
		return platform;
	}

	public List<Game> getGameByPlatform(String name) {
		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM PLATFORM WHERE UPPER(NAME) = UPPER(?)";
		Query<Platform> q = s.createNativeQuery(sql, Platform.class);
		q.setParameter(1, name);
		Platform platform = q.uniqueResult();
		System.out.println(platform);
		sql = "SELECT * FROM GAME LEFT JOIN GAME_PLATFORM ON GAME_PLATFORM.GAME_ID = GAME.GAME_ID WHERE GAME_PLATFORM.PLATFORM_ID = ?";
		Query<Game> qG = s.createNativeQuery(sql, Game.class);
		qG.setParameter(1, platform.getId());
		return qG.list();

	}

	@Override
	public int addPlatform(Platform platform) {

		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int platformPk = (Integer) s.save(platform);
		tx.commit();
		s.close();
		return platformPk;

	}
	
	public List<Platform> getAllPlatforms(){
		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM Platform";
		Query<Platform> q = s.createNativeQuery(sql, Platform.class);
		return q.list();
	}

	@Override
	public void updatePlatform(Platform platform) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.merge(platform);
		tx.commit();
		s.close();
	}

	public boolean platformExists(Platform platform) {
		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM PLATFORM WHERE NAME = ?";
		Query<Platform> q = s.createNativeQuery(sql, Platform.class);
		q.setParameter(1, platform.getName());
		return (q.uniqueResult() != null);
	}

}
