package com.example.demo.DTO;

public class AllArtistsDTO {
	
	private Long id;
	private String name;
	private String image;
	
	public AllArtistsDTO() {
		
	}

	public AllArtistsDTO(Long id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image = image;
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

}
