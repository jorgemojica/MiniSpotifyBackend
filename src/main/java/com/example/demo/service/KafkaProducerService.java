package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AllArtistsDTO;

@Service
public class KafkaProducerService {
	
	@Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, AllArtistsDTO> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, AllArtistsDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(AllArtistsDTO artist) {
        kafkaTemplate.send(topicName, artist);
    }

}
