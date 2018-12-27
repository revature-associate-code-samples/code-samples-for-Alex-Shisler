package com.revature.util;

import java.util.concurrent.ExecutionException;

import com.revature.dao.GameDAO;
import com.revature.dao.GameDAOImpl;
import com.revature.dao.GenreDAO;
import com.revature.dao.GenreDAOImpl;
import com.revature.dao.PlatformDAO;
import com.revature.dao.PlatformDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.igdb.IgdbRequest;
import com.revature.model.Game;
import com.revature.model.GameUser;

public class Driver {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		UserDAO ud = new UserDAOImpl();
		GameDAO gd = new GameDAOImpl();
		IgdbRequest igd = new IgdbRequest();
		PlatformDAO pDAO = new PlatformDAOImpl();
		GenreDAO gnDAO = new GenreDAOImpl();
		
		GameUser user1 = new GameUser("Drago", "got@gmail.com", "winteriscoming", "user");
		Game myGame = gd.getGameByName("Dragon Age: Origins");
		user1.setFavGame(myGame);
		user1.setGenre("Shooter");
		ud.createUser(user1);
		 
		System.out.println(ud.getUser("Drago"));
		// System.out.println(gd.getGameByGenre());
		// System.out.println(gd.getGameByGenre("Shooter"));
		// System.out.println(gd.searchGameByName("zelda"));
		// igd.getGameByTitle("Dark Souls");
		//System.out.println(gnDAO.getAllGenres());
		// System.out.println(pDAO.getGameByPlatform("Playstation 4"));
	}

}
