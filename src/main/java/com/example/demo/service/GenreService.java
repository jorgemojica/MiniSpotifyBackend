package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.AllGenresDTO;
import com.example.demo.DTO.GenreDetailDTO;
import com.example.demo.DTO.TrackDTO;
import com.example.demo.entity.Genre;
import com.example.demo.repository.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	private GenreRepository genreRepository;
	
	public GenreService(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}
	
	public List<AllGenresDTO> getAllGenres(){
		return this.genreRepository.findAll().stream().map(genre -> new AllGenresDTO(genre.getId(), genre.getName())).collect(Collectors.toList());
	}
	
	public GenreDetailDTO getGenreById(Long id){
		Genre genre = this.genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not found"));
		
		List<TrackDTO> trackDTOs = genre.getTracks().stream()
				.map(track -> new TrackDTO(track.getId(), track.getTitle(), track.getDuration())).collect(Collectors.toList());
		
		return new GenreDetailDTO(genre.getId(), genre.getName(), trackDTOs);
	}
	
	public Genre createGenre(Genre genre){
		return this.genreRepository.save(genre);
	}
	
//	public String deleteGenre(Long id) {
//		Optional<Genre> genreOpt = this.getGenreById(id);
//		if(genreOpt.isPresent()) {
//			this.genreRepository.delete(genreOpt.get());
//			return "The genre \"" + genreOpt.get().getName() + "\" has been deleted successfully";
//		}
//		else {
//			return "The genre has not been deleted successfully";
//		}
//	}
	
//	public Genre updatedGenre(Long id, Genre genreParam) {
//		Optional<Genre> genreOpt = this.getGenreById(id);
//		if(genreOpt.isPresent()) {
//			Genre genre = genreOpt.get();
//			genre.setName(genreParam.getName());
//			genre.setTracks(genreParam.getTracks());
//			return this.genreRepository.save(genre);
//		}
//		else {
//			return null;
//		}
//	}

}
