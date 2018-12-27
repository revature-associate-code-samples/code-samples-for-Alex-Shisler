package com.revature.dao;

import java.util.List;

import com.revature.model.Game;

public interface GameDAO {
Game getGameByName(String name);	
List<Game>getGamesByCompany(String company);
List<Game>getGamesByPlatform(String platform);
List<Game>getGameByGenre(String genre);
int addGame(Game game);
List<Game> searchGameByName(String name);
List<Game> getAllGames();

}
