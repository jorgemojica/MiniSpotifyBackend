package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AllArtistsDTO;
import com.example.demo.DTO.ArtistDetailDTO;
import com.example.demo.entity.Artist;
import com.example.demo.service.ArtistService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/artist")
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	public ArtistController(ArtistService artistService) {
		this.artistService = artistService;
	}
	
	@GetMapping
	public List<AllArtistsDTO> listArtists(){
		return this.artistService.listArtists();
	}
	
	@RequestMapping(value = "{id}")
	public ResponseEntity<ArtistDetailDTO> getArtistById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.artistService.getArtistById(id));
	}
	
//	@DeleteMapping(value = "{id}")
//	public ResponseEntity<String> deleteArtist(@PathVariable("id") Long id) {
//		return ResponseEntity.ok(this.artistService.deleteArtist(id));
//	}
	
	@PostMapping
	public ResponseEntity<Artist> createArtist(@RequestBody Artist newArtist) {
		Artist artist = this.artistService.createArtist(newArtist);
		return ResponseEntity.ok(artist);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Artist> updateArtist(@PathVariable("id") Long id, @RequestBody Artist artist){
		Artist updatedArtist = this.artistService.updateArtist(id, artist);
		return ResponseEntity.ok(updatedArtist);
	}

}
