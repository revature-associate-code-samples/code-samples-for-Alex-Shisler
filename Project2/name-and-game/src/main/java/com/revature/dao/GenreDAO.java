package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Game;
import com.revature.model.Genre;

public interface GenreDAO {
	public Genre getGenre(String name);
	public int addGenre(Genre genre);
	public boolean genreExists(Genre genre);
	public void updateGenre(Genre genre);
	public List<Game> getGameByGenre(String name);
	public List<Genre> getAllGenres();
}
