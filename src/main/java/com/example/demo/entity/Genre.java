package com.example.demo.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id")
	private Long id;
	
	@JoinColumn(name = "name")
	private String name;
	
	@ManyToMany
	@JoinTable(
			name = "genre_track",
			joinColumns = @JoinColumn(name = "genre_id"),
			inverseJoinColumns = @JoinColumn(name = "track_id")
			)
	private List<Track> tracks;
	
	public Genre() {
		
	}

	public Genre(Long id, String name, List<Track> tracks) {
		this.id = id;
		this.name = name;
		this.tracks = tracks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

}
