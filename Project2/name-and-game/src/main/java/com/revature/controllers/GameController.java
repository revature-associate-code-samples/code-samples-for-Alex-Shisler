package com.revature.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.GameDAO;
import com.revature.dao.GameDAOImpl;
import com.revature.dao.GenreDAO;
import com.revature.dao.GenreDAOImpl;
import com.revature.dao.PlatformDAO;
import com.revature.dao.PlatformDAOImpl;
import com.revature.igdb.IgdbRequest;
import com.revature.model.Game;
import com.revature.model.Genre;
import com.revature.model.Platform;

@CrossOrigin
@Controller
@RequestMapping("/game")
public class GameController {
	private GameDAO gd = new GameDAOImpl();
	private IgdbRequest ib = new IgdbRequest();
	private GenreDAO gnD = new GenreDAOImpl();
	private PlatformDAO pD = new PlatformDAOImpl();
	//--------------------------Variables---------------------------------------//
	
	
	@GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGameByName(@PathVariable("name") String name) {

		Game game = gd.getGameByName(name);

		System.out.println(game);
		try {
			return new ObjectMapper().writeValueAsString(game);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error Parsing Data");
		}
	}

	@GetMapping(value = "search/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGamesByName(@PathVariable("name") String name) {

		List<Game> games = gd.searchGameByName(name);
		System.out.println(games);
		if (games.isEmpty()) {
			ib.getGameByTitle(name);
			games = gd.searchGameByName(name);
		}
		try {
			return new ObjectMapper().writeValueAsString(games);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error Parsing Data");
		}
	}
	
	@GetMapping(value = "allGames", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllGames() {
		List<Game> games =  gd.getAllGames();
		try {
			return new ObjectMapper().writeValueAsString(games);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error Parsing Data");
		}
	}
	
	//----------------------------Search Name------------------------------------------//

	@GetMapping(value = "searchGenre/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGamesByGenre(@PathVariable("name") String name) {
		List<Game> games = gnD.getGameByGenre(name);
		try {
			return new ObjectMapper().writeValueAsString(games);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error Parsing Data");
		}
		
	}
	
	@GetMapping(value = "genres", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGenres() {
		List<Genre> genres = gnD.getAllGenres();
		try {
			return new ObjectMapper().writeValueAsString(genres);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error Parsing Data");
		}
	}
	
	//-----------------------------Genres--------------------------------------------//
	@GetMapping(value = "platforms", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPlatforms() {
		List<Platform> platforms= pD.getAllPlatforms();
		try {
			return new ObjectMapper().writeValueAsString(platforms);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error Parsing Data");
		}
	}
	
	@GetMapping(value = "searchPlatform/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGamesByPlatform(@PathVariable("name") String name) {
		List<Game> games = pD.getGameByPlatform(name);
		try {
			return new ObjectMapper().writeValueAsString(games);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error Parsing Data");
		}
		
	}
	//-----------------------------Platforms-------------------------------------//
	
	@GetMapping(value = "dev/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getGamesByCompany(@PathVariable("name") String name) {
		List<Game> games = gd.getGamesByCompany(name);
		if (games.isEmpty()) {
			// ib.getGameByTitle(name);
			games = gd.searchGameByName(name);
		}
		try {
			return new ObjectMapper().writeValueAsString(games);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error Parsing Data");
		}
	}
	
	//----------------------------Companies-------------------------------------//

}
