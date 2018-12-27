package com.revature.dao;

import java.util.List;

import com.revature.model.GameUser;

public interface UserDAO {
	int createUser(GameUser user);
	void updateUser(GameUser user);
	GameUser getUser(String name);
	GameUser getUserbyId(int id);
	void deleteUser(int id);
	List<GameUser> getAllUsers();
	GameUser login(String email, String password);
	
}
