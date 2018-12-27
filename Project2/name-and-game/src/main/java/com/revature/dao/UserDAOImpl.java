package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.model.GameUser;
import com.revature.util.HibernateUtil;

public class UserDAOImpl implements UserDAO{

	public int createUser(GameUser user) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int userPk = (Integer) s.save(user);
		tx.commit();
		s.close();
		return userPk;
	}
	public void updateUser(GameUser user) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.update(user);
		tx.commit();
		s.close();
	}

	public GameUser getUser(String name) {

		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM GAMEUSER WHERE USERNAME = ?";
		Query<GameUser> q = s.createNativeQuery(sql, GameUser.class);
		q.setParameter(1, name);
		return q.uniqueResult();
	}

	public List<GameUser> getAllUsers() {
		String sql = "SELECT * FROM GAMEUSER";
		Session s = HibernateUtil.getSession();
		Query<GameUser> q = s.createNativeQuery(sql, GameUser.class);
		List<GameUser> users = q.list();
		s.close();
		return users;
	}
	@Override
	public GameUser getUserbyId(int id) {

		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM GAMEUSER WHERE USER_ID = ?";
		Query<GameUser> q = s.createNativeQuery(sql, GameUser.class);
		q.setParameter(1, id);
		GameUser user = q.uniqueResult();
		return user;
	}
	@Override
	public void deleteUser(int id) {

		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		GameUser user = s.get(GameUser.class, id);
		if(user != null) {
			s.delete(user);
		}
		tx.commit();
		s.close();
	}
	
	  @Override
	   public GameUser login(String email, String password) {
	       // TODO Auto-generated method stub
	       Session s = HibernateUtil.getSession();
	       String sql = "Select * from GAMEUSER" + " where EMAIL = ? AND PASSWORD = ?";
	       Query<GameUser> q = s.createNativeQuery(sql, GameUser.class);
	       q.setParameter(1, email);
	       q.setParameter(2, password);
	       return q.uniqueResult();

	   }

}
