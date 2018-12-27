package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class GameUser {
	@Id
	@Column(name = "User_ID")
	@SequenceGenerator(name = "userSequence", allocationSize = 1, sequenceName = "SQ_USER_PK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
	private int id;

	@Column(name = "USERNAME", unique = true)
	private String username;

	@Column(name = "EMAIL", unique = true)
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "FAVORITEGENRE")
	private String genre;
	
	@ManyToOne
	@JoinColumn(name = "GAME_ID")
	private Game favGame;

	public GameUser() {
		super();
	}

	public GameUser(String username, String email, String password, String role) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	
	public GameUser(int id, String username, String email, String password, String role, String genre, Game favGame) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.genre = genre;
		this.favGame = favGame;
	}
	public GameUser(String username, String email, String pass, String role, String favoritegenre, Game favGame) {
		super();
		this.username = username;
		this.email = email;
		this.password = pass;
		this.role = "user";
		this.genre  = favoritegenre;
		this.favGame = favGame;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Game getFavGame() {
		return favGame;
	}

	public void setFavGame(Game favGame) {
		this.favGame = favGame;
	}

	@Override
	public String toString() {
		return "GameUser [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", role=" + role + ", genre=" + genre + ", favGame=" + favGame + "]";
	}

}
