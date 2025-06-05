package com.example.demo.DTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.entity.Playlist;
import com.example.demo.entity.Role;
import com.example.demo.entity.Subscription;

public class UserDetailDTO {
	
	private Long id;
	private String username;
	private String password;
	private String email;
	private String name;
	private String image;
	private Set<Role> roles;
	private Subscription subscription;
	private List<Playlist> playlists;
	
	public UserDetailDTO() {
		
	}

	public UserDetailDTO(Long id, String username, String password, String email, String name, String image, Set<Role> roles,
			Subscription subscription, List<Playlist> playlists) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.image = "";
		this.roles = new HashSet<>();
		this.subscription = subscription;
		this.playlists = playlists;
	}
	
	public UserDetailDTO(Long id, String username, String email, String password, String name, String image, Set<Role> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.image = image;
		this.roles = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

}
