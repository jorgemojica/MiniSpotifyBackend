package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AllArtistsDTO;
import com.example.demo.DTO.ArtistDetailDTO;
import com.example.demo.DTO.TrackDTO;
import com.example.demo.entity.Artist;
import com.example.demo.repository.ArtistRepository;

@Service
public class ArtistService {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	public ArtistService(ArtistRepository artistRepository) {
		this.artistRepository = artistRepository;
	}
	
	public List<AllArtistsDTO> listArtists() {
		List<AllArtistsDTO> allArtistsDTOs = this.artistRepository.findAll().stream().
				map(artist -> new AllArtistsDTO(artist.getId(), artist.getName(), artist.getImage())).collect(Collectors.toList());
		return allArtistsDTOs;
	}
	
	public ArtistDetailDTO getArtistById(Long id) {
		Artist artist = this.artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));
		
		List<TrackDTO> trackDTOs = artist.getTracks().stream()
				.map(track -> new TrackDTO(track.getId(), track.getTitle(), track.getDuration())).collect(Collectors.toList());
		
		return new ArtistDetailDTO(artist.getId(), artist.getName(), artist.getCountry(), artist.getImage(), trackDTOs);
	}
	
//	public String deleteArtist(Long id) {
//		Optional<Artist> artist = this.getArtistById(id);
//		if(artist.isPresent()) {
//			this.artistRepository.deleteById(id);
//			return "The artist \"" + artist.get().getName() + "\" has been deleted successfully";
//		}
//		else {
//			return "The artist " + artist.get().getName() + " has been deleted";
//		}
//		
//	}
	
	public Artist createArtist(Artist newArtist) {
		Artist artist = new Artist();
		if(!newArtist.equals(null)) {
			artist.setName(newArtist.getName());
			artist.setCountry(newArtist.getCountry());
			return this.artistRepository.save(artist);
		}
		else {
			return null;
		}
	}
	
	public Artist updateArtist(Long id, Artist udpArtist) {
		Optional<Artist> artist = this.artistRepository.findById(id);
		if(artist.isPresent()) {
			Artist newArtist = artist.get();
			newArtist.setName(udpArtist.getName());
			newArtist.setCountry(udpArtist.getCountry());
			newArtist.setImage(udpArtist.getImage());
			this.artistRepository.save(newArtist);
			return newArtist;
		}
		else {
			return null;
		}
	}

}
