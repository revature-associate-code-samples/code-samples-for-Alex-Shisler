package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Platform {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="platformSequence")
	@SequenceGenerator(name="platformSequnce", allocationSize=1, sequenceName="SQ_PLATFORM_PK")
	@Column(name="PLATFORM_ID")
	private int id;
	
	@Column(unique = true)
	private String name;

	@ManyToMany(mappedBy = "platforms")
	private List<Game> games = new ArrayList<>();
	
	public Platform() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Platform(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Platform [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Platform other = (Platform) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
}

