package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTO.AllGenresDTO;
import com.example.demo.DTO.GenreDetailDTO;
import com.example.demo.entity.Genre;
import com.example.demo.service.GenreService;

@RestController
@RequestMapping("api/genre")
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	
	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}
	
	@GetMapping
	public List<AllGenresDTO> getAllGenres(){
		return this.genreService.getAllGenres();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GenreDetailDTO> getGenreById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.genreService.getGenreById(id));
	}
	
	@PostMapping
	public ResponseEntity<Genre> createGenre(@RequestBody Genre genreParam){
		return ResponseEntity.ok(this.genreService.createGenre(genreParam));
	}
	
//	@DeleteMapping("{id}")
//	public ResponseEntity<String> deleteGenre(@PathVariable("id") Long id){
//		return ResponseEntity.ok(this.genreService.deleteGenre(id));
//	}
	
//	@PutMapping("{id}")
//	public ResponseEntity<Genre> updateGenre(@PathVariable("id") Long id, @RequestBody Genre genreParam){
//		return ResponseEntity.ok(this.genreService.updatedGenre(id, genreParam));
//	}

}
