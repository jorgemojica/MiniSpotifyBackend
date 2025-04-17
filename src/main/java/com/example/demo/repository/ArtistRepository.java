package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.DTO.TrackDTO;
import com.example.demo.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
	
	List<Artist> findByCountry(String country);
	
	@Query(value = "SELECT new com.example.demo.DTO.TrackDTO(t.id, t.title, t.duration) " +
		    "FROM Track t " +
		    "JOIN t.artist a " +
		    "WHERE a.country = :country")
	List<TrackDTO> findTracksByCountry(@Param("country") String country);

}
