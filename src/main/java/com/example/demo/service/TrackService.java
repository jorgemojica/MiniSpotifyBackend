package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.TrackDTO;
import com.example.demo.entity.Track;
import com.example.demo.repository.TrackRepository;

@Service
public class TrackService {
	
	@Autowired
	private TrackRepository trackRepository;
	
	public TrackService(TrackRepository trackRepository) {
		this.trackRepository = trackRepository;
	}
	
	public List<TrackDTO> getAllTracks(){
		List<TrackDTO> trackDTOs = this.trackRepository.findAll().stream()
				.map(track -> new TrackDTO(track.getId(), track.getTitle(), track.getDuration())).collect(Collectors.toList());
		return trackDTOs;
	}
	
	public Optional<Track> getTrackById(Long id) {
		return this.trackRepository.findById(id);
	}
	
	public Track createTrack(Track track) {
		return this.trackRepository.save(track);
	}
	
	public String deleteTrack(Long id) {
		Optional<Track> trackOpt = this.getTrackById(id);
		if(trackOpt.isPresent()) {
			this.trackRepository.deleteById(id);
			return "The track \"" + trackOpt.get().getTitle() + "\" has been deleted successfully";
		}
		else {
			return "The track \"" + trackOpt.get().getTitle() + "\" has not been deleted successfully";
		}
		
	}
	
	public Track updateTrack(Long id, Track trackUpd) {
		Optional<Track> trackOpt = this.getTrackById(id);
		if(trackOpt.isPresent()) {
			Track track = trackOpt.get();
			track.setTitle(trackUpd.getTitle());
			track.setDuration(trackUpd.getDuration());
			return this.trackRepository.save(track);
		}
		else {
			return null;
		}
	}

}
