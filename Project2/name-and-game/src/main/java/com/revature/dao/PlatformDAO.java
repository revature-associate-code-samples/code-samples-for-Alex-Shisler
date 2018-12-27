package com.revature.dao;

import java.util.List;

import com.revature.model.Game;
import com.revature.model.Platform;

public interface PlatformDAO {
	public Platform getPlatform(String name);
	public int addPlatform(Platform platform);
	public boolean platformExists(Platform platform);
	public void updatePlatform(Platform platform);
	public List<Game> getGameByPlatform(String name);
	public List<Platform> getAllPlatforms();
}
