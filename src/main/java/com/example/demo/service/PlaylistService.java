package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AllPlaylistDTO;
import com.example.demo.DTO.PlaylistDetailDTO;
import com.example.demo.DTO.TrackDTO;
import com.example.demo.entity.Playlist;
import com.example.demo.repository.PlaylistRepository;

@Service
public class PlaylistService {
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	public PlaylistService(PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}
	
	public List<AllPlaylistDTO> getAllPlaylists(){
		return this.playlistRepository.findAll().stream()
				.map(playlist -> new AllPlaylistDTO(playlist.getId(), playlist.getName(), playlist.getImage())).collect(Collectors.toList());
	}
	
	public PlaylistDetailDTO getPlaylistById(Long id){
		Playlist playlist = this.playlistRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Playlist not found"));
		
		List<TrackDTO> trackDTOs = playlist.getTracks().stream()
				.map(track -> new TrackDTO(track.getId(), track.getTitle(), track.getDuration())).collect(Collectors.toList());
		
		return new PlaylistDetailDTO(playlist.getId(), playlist.getName(), playlist.getImage(), trackDTOs);
	}
	
	public Playlist createPlaylist(Playlist playlist) {
		return this.playlistRepository.save(playlist);
	}
	
//	public String deletePlaylist(Long id) {
//		Optional<Playlist> playlistOpt = this.getPlaylistById(id);
//		if(playlistOpt.isPresent()) {
//			this.playlistRepository.delete(playlistOpt.get());
//			return "The playlist \"" + playlistOpt.get().getName() + "\" has been deleted successfully";
//		}
//		else {
//			return "The playlist has not been deleted successfully";
//		}
//	}
//	
//	public Playlist updatePlaylist(Long id, Playlist playlistParam) {
//		Optional<Playlist> playlistOpt = this.getPlaylistById(id);
//		if(playlistOpt.isPresent()) {
//			Playlist playlist = playlistOpt.get();
//			playlist.setName(playlistParam.getName());
//			return this.playlistRepository.save(playlist);
//		}
//		else {
//			return null;
//		}
//	}

}
