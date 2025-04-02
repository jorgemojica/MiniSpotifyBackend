package com.example.demo.DTO;

import java.util.List;

public class PlaylistDetailDTO {
	
	private Long id;
	private String name;
	private String image;
	private List<TrackDTO> tracks;
	
	public PlaylistDetailDTO() {
		
	}

	public PlaylistDetailDTO(Long id, String name, String image, List<TrackDTO> tracks) {
		this.id = id;
		this.name = name;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<TrackDTO> getTracks() {
		return tracks;
	}

	public void setTracks(List<TrackDTO> tracks) {
		this.tracks = tracks;
	}

}
