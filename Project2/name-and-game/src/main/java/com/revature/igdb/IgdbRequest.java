package com.revature.igdb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.dao.GameDAO;
import com.revature.dao.GameDAOImpl;
import com.revature.dao.GenreDAO;
import com.revature.dao.GenreDAOImpl;
import com.revature.dao.PlatformDAO;
import com.revature.dao.PlatformDAOImpl;
import com.revature.model.Game;
import com.revature.model.Genre;
import com.revature.model.Platform;

import callback.OnSuccessCallback;
import wrapper.IGDBWrapper;
import wrapper.Parameters;
import wrapper.Version;

public class IgdbRequest {
	// private String key = "c7b9a8a0deb857507ca18673690dd26b";
	// private String key = "b7f579b4334207852addd702fe0e3442";
	private String key = "15131b99aecc40473d93560443facb43";
	private IGDBWrapper wrapper = new IGDBWrapper(key, Version.STANDARD, true);
	private JSONArray gameInfo;
	boolean success = false;
	private GameDAO gDAO = new GameDAOImpl();
	private PlatformDAO pDAO = new PlatformDAOImpl();
	private GenreDAO gnDAO = new GenreDAOImpl();

	public void getGameByTitle(String title) {
		gameInfo = null;
		success = false;

		Parameters params = new Parameters().addSearch(title).addFields("name,first_release_date")
				.addFilter("[version_parent][not_exists]=1").addExpand("genres,developers,platforms").addLimit("15");
		wrapper.games(params, new OnSuccessCallback() {
			public void onSuccess(JSONArray result) {
				// JSONArray containing 2 titles
				System.out.println("Success!");
				System.out.println(result);
				gameInfo = result;

				createGame(gameInfo);
			}

			public void onError(Exception error) {
				System.out.println("Error");// Do something on error
			}
		});

	}

	public List<Game> getGamesByName() {
		return null;
	}

	public List<Game> getGamesByCompany(String company) {
		return null;
	}

	public List<Game> getGamesByPlatform(String platform) {
		return null;

	}

	public List<Game> getGamesByYear(int year) {
		return null;

	}

	public List<Game> getGameByGenre(String genre) {
		return null;

	}

//-----------------------Helper Methods-------------------------//
	private void createGame(JSONArray result) {
		List<Game> newGames = new ArrayList<>();
		for (int i = 0; i < result.length(); i++) {
			Game newGame = new Game();

			newGame.setTitle(result.getJSONObject(i).getString("name"));
			newGame.setCompany(getName(result.getJSONObject(i), "developers"));
			newGame.setPlatforms(getPlatformList(result.getJSONObject(i)));
			newGame.setGenres(getGenreList(result.getJSONObject(i)));
			if (result.getJSONObject(i).has("first_release_date")) {
				Date date = new Date((Long) result.getJSONObject(i).get("first_release_date"));
				newGame.setReleaseDate(date);
			}
			System.out.println(newGame.toString());
			newGames.add(newGame);
			gDAO.addGame(newGame);
		} // result array
		System.out.println(newGames);

	}// createGame

	// returns the first name value of a given key (used for company and name)
	private String getName(JSONObject result, String data) {
		if (result.has(data)) {
			JSONArray array = (JSONArray) result.get(data);
			return array.getJSONObject(0).getString("name");
		} // getDeveloper name if it has one

		return "N/A";
	}// getName

	private Set<Platform> getPlatformList(JSONObject result) {
		Set<Platform> nameList = new HashSet<>();
		if (result.has("platforms")) {
			JSONArray array = (JSONArray) result.get("platforms");
			for (int x = 0; x < array.length(); x++) {
				Platform newPlat = new Platform(array.getJSONObject(x).getString("name"));
				if (pDAO.platformExists(newPlat)) {
					newPlat = pDAO.getPlatform(newPlat.getName());
					pDAO.updatePlatform(newPlat);
				} else {
					pDAO.addPlatform(newPlat);
				}
				nameList.add(newPlat);
			} // developer array
			return nameList;
		} // getDeveloper name if it has one

		Platform na = new Platform("N/A");
		if (pDAO.platformExists(na)) {
			na = pDAO.getPlatform("N/A");
			pDAO.updatePlatform(na);
		} else {
			pDAO.addPlatform(na);
		}
		nameList.add(na);
		return nameList;
	}

	private Set<Genre> getGenreList(JSONObject result) {
		Set<Genre> nameList = new HashSet<>();
		if (result.has("genres")) {
			JSONArray array = (JSONArray) result.get("genres");
			for (int x = 0; x < array.length(); x++) {
				Genre newGN = new Genre(array.getJSONObject(x).getString("name"));
				if (gnDAO.genreExists(newGN)) {
					newGN = gnDAO.getGenre(newGN.getName());
					gnDAO.updateGenre(newGN);
				} else {
					gnDAO.addGenre(newGN);
				}
				nameList.add(newGN);
			} // developer array
			return nameList;
		} // getGenre name if it has one
		else
			System.out.println("N/A");
		Genre na = new Genre("N/A");
		if (gnDAO.genreExists(na)) {
			na = gnDAO.getGenre("N/A");
			gnDAO.updateGenre(na);
		} else {
			gnDAO.addGenre(na);
		}
		nameList.add(na);
		return nameList;
	}

	// ---------------Helper Methods End---------------------//

}
