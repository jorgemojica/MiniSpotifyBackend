package com.example.demo.DTO;

public class TrackDTO {
	
	private Long id;
	private String title;
	private String duration;
	
	public TrackDTO() {
	}
	
	public TrackDTO(Long id, String title, String duration) {
		this.id = id;
		this.title = title;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
