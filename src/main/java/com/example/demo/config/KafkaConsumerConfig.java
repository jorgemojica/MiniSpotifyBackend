package com.example.demo.config;

import com.example.demo.DTO.AllArtistsDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration

public class KafkaConsumerConfig {
	
	@Bean
    public ConsumerFactory<String, AllArtistsDTO> artistConsumerFactory() {
        JsonDeserializer<AllArtistsDTO> deserializer = new JsonDeserializer<>(AllArtistsDTO.class);
        deserializer.addTrustedPackages("*");

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "grupo-spotify");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AllArtistsDTO> artistKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AllArtistsDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(artistConsumerFactory());
        return factory;
    }

}
