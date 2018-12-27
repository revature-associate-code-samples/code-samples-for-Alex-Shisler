package com.revature.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table
public class Game {

	@Id
	@Column(name = "Game_ID")
	@SequenceGenerator(name = "gameSequence", allocationSize = 1, sequenceName = "SQ_GAME_PK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameSequence")
	private int id;
	@Column(name = "TITLE", unique = true)
	private String title;
	@Column(name = "REVIEW")
	private String review;
	@Column(name = "COMPANY")
	private String company;
	@Column(name = "RELEASEDATE")
	private Date releaseDate;

//@Transient
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "GAME_GENRE",
			joinColumns = { @JoinColumn(name = "GAME_ID") },
			inverseJoinColumns = {@JoinColumn(name = "GENRE_ID")}
			)
	private Set<Genre> genres = new HashSet<>();

//@Transient
	@ManyToMany(fetch = FetchType.EAGER)

	@JoinTable(
			name = "GAME_PLATFORM", 
			joinColumns = { @JoinColumn(name = "GAME_ID") },
			inverseJoinColumns = {@JoinColumn(name = "PLATFORM_ID")}
			)
	private Set<Platform> platforms = new HashSet<>();

	public Game() {
		// TODO Auto-generated constructor stub
	}

	public Game(int id, String title, String review, String company, Date releaseDate) {
		super();
		this.id = id;
		this.title = title;

		this.review = review;
		this.company = company;
		this.releaseDate = releaseDate;
	}
	public Game(String title, String review, String company, Date releaseDate) {
		super();
		this.title = title;
		this.review = review;
		this.company = company;
		this.releaseDate = releaseDate;
	}
	
	
	public Game(String title, String review, String company, Date releaseDate, Set<Genre> genres,
			Set<Platform> platforms) {
		super();
		this.title = title;
		this.review = review;
		this.company = company;
		this.releaseDate = releaseDate;
		this.genres = genres;
		this.platforms = platforms;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public Set<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(Set<Platform> platforms) {
		this.platforms = platforms;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	

	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", review=" + review + ", company=" + company + ", releaseDate="
				+ releaseDate + ", genres=" + genres + ", platforms=" + platforms + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + id;
		result = prime * result + ((platforms == null) ? 0 : platforms.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((review == null) ? 0 : review.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (id != other.id)
			return false;
		if (platforms == null) {
			if (other.platforms != null)
				return false;
		} else if (!platforms.equals(other.platforms))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	

}
