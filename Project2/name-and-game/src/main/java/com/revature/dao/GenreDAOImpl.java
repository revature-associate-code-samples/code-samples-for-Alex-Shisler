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

public class GenreDAOImpl implements GenreDAO {

	@Override
	public int addGenre(Genre genre) {
		// TODO Auto-generated method stub

		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int genrePk = (Integer) s.save(genre);
		tx.commit();
		s.close();
		return genrePk;
	}
	@Override
	public void updateGenre(Genre genre) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		genre = getGenre(genre.getName());
		s.merge(genre);
		tx.commit();
		s.close();
	}

	@Override
	public Genre getGenre(String name) {
		// TODO Auto-generated method stub
		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM GENRE WHERE NAME = ?";
		Query<Genre> q = s.createNativeQuery(sql, Genre.class);
		q.setParameter(1, name);
		Genre genre = q.uniqueResult();
		return genre;
	}
	
	public List<Genre> getAllGenres(){
		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM GENRE";
		Query<Genre> q = s.createNativeQuery(sql, Genre.class);
		return q.list();
	}

	@Override
	public boolean genreExists(Genre genre) {
		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM GENRE WHERE NAME = ?";
		Query<Genre> q = s.createNativeQuery(sql, Genre.class);
		q.setParameter(1, genre.getName());
		return (q.uniqueResult() != null);
	}
	
	public List<Game> getGameByGenre(String name){
		Session s = HibernateUtil.getSession();
		String sql = "SELECT * FROM GENRE WHERE UPPER(NAME) = UPPER(?)";
		Query<Genre> q = s.createNativeQuery(sql, Genre.class);
		q.setParameter(1, name);
		Genre genre= q.uniqueResult();
		System.out.println(genre);
		sql = "SELECT * FROM GAME LEFT JOIN GAME_GENRE ON GAME_GENRE.GAME_ID = GAME.GAME_ID WHERE GAME_GENRE.GENRE_ID = ?";
		Query<Game> qG = s.createNativeQuery(sql, Game.class);
		qG.setParameter(1, genre.getId());
		return qG.list();
	}
}
