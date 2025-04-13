package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.TrackDTO;
import com.example.demo.entity.Track;
import com.example.demo.service.TrackService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/track")
public class TrackController {
	
	private TrackService trackService;
	
	public TrackController(TrackService trackService) {
		this.trackService =  trackService;
	}
	
	@GetMapping
	public List<TrackDTO> getAllTracks(){
		return this.trackService.getAllTracks();
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Track> getTrackById(@PathVariable("id") Long id){
		Optional<Track> track = this.trackService.getTrackById(id);
		if(track.isPresent()){
			return ResponseEntity.ok(track.get()); 
		}
		else {
			return null;
		}
	}
	
	@PostMapping
	public ResponseEntity<Track> createTrack(@RequestBody Track newTrack){
		Track track = this.trackService.createTrack(newTrack);
		if(track != null) {
			return ResponseEntity.ok(track);
		}
		else {
			return null;
		}
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> deleteTrack(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.trackService.deleteTrack(id));
	}
	
	@PatchMapping(value = "{id}")
	public ResponseEntity<Track> updateTrack(@PathVariable("id") Long id, @RequestBody Track track){
		return ResponseEntity.ok(this.trackService.updateTrack(id, track));
	}

}
