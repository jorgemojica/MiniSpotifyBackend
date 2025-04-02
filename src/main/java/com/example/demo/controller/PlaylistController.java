package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AllPlaylistDTO;
import com.example.demo.DTO.PlaylistDetailDTO;
import com.example.demo.entity.Playlist;
import com.example.demo.service.PlaylistService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/playlist")
public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	public PlaylistController(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}
	
	@GetMapping
	public List<AllPlaylistDTO> getAllPlaylists(){
		return this.playlistService.getAllPlaylists();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PlaylistDetailDTO> getPlaylistById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.playlistService.getPlaylistById(id));
	}
	
	@PostMapping
	public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist){
		return ResponseEntity.ok(this.playlistService.createPlaylist(playlist));
	}
	
//	@DeleteMapping("{id}")
//	public ResponseEntity<String> deletePlaylist(@PathVariable("id") Long id){
//		return ResponseEntity.ok(this.playlistService.deletePlaylist(id));
//	}
//	
//	@PutMapping("{id}")
//	public ResponseEntity<Playlist> updatePlaylist(@PathVariable("id") Long id, @RequestBody Playlist playlistParam){
//		return ResponseEntity.ok(this.playlistService.updatePlaylist(id, playlistParam));
//	}

}
