package com.example.demo.DTO;

import java.util.List;

public class GenreDetailDTO {
	
	private Long id;
	private String name;
	private List<TrackDTO> tracks;
	
	public GenreDetailDTO() {
		
	}

	public GenreDetailDTO(Long id, String name, List<TrackDTO> tracks) {
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

	public List<TrackDTO> getTracks() {
		return tracks;
	}

	public void setTracks(List<TrackDTO> tracks) {
		this.tracks = tracks;
	}

}
