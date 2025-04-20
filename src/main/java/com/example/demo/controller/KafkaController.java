package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AllArtistsDTO;
import com.example.demo.service.KafkaProducerService;

@RestController
@RequestMapping("api/kafka")
public class KafkaController {
	
	private final KafkaProducerService kafkaProducerService;

    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendArtist(@RequestBody AllArtistsDTO artist) {
        kafkaProducerService.sendMessage(artist);
        return ResponseEntity.ok("Artista enviado a Kafka: " + artist.getName());
    }

}
