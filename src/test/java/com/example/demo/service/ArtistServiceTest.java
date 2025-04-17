package com.example.demo.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.AllArtistsDTO;
import com.example.demo.DTO.ArtistDetailDTO;
import com.example.demo.entity.Artist;
import com.example.demo.entity.Playlist;
import com.example.demo.entity.Track;
import com.example.demo.repository.ArtistRepository;

@SpringBootTest
public class ArtistServiceTest {
	
	@Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistRepository artistRepository;

    @BeforeEach
    void setup() {
        artistRepository.deleteAll(); // Limpiar la base antes de cada test
        
        Artist artist1 = new Artist(null, "Black Veil Brides", "US", "bvb.jpg", null);
        Artist artist2 = new Artist(null, "All Time Low", "US", "ATL.jpg", null);

        artistRepository.save(artist1);
        artistRepository.save(artist2);

    }

    @Test
    void shouldReturnAListOfArtists() {
        List<AllArtistsDTO> result = artistService.listArtists();
        
//        Assertions.assertEquals(3, result.size());
        
        List<String> names = result.stream().map(AllArtistsDTO::getName).collect(Collectors.toList());
        List<String> countries = result.stream().map(AllArtistsDTO::getCountry).collect(Collectors.toList());
        
        Assertions.assertTrue(names.contains("Black Veil Brides"));
        Assertions.assertTrue(names.contains("All Time Low"));
        Assertions.assertTrue(countries.contains("US"));
    }
    
    @Test
    @Transactional
    void shouldReturnArtistById() {
    	
        Artist artist3 = new Artist(null, "Silverstein", "CA", "Silverstein.jpg", new ArrayList<Track>() {{
            add(new Track(null, "My Heroine",   "3:20", null, null, null));
            add(new Track(null, "",   "4:05", null, null, null));
        }}
    );
        artist3 = artistRepository.save(artist3);

        Long artistId = artist3.getId();

        ArtistDetailDTO artistDetailDTO = artistService.getArtistById(artistId);

        Assertions.assertEquals(artist3.getId(), artistDetailDTO.getId());
        Assertions.assertEquals(artist3.getName(), artistDetailDTO.getName());
        Assertions.assertEquals(artist3.getCountry(), artistDetailDTO.getCountry());
        Assertions.assertEquals(artist3.getImage(), artistDetailDTO.getImage());
    }

}
