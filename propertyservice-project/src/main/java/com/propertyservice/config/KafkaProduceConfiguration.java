package com.propertyservice.config;

import java.util.HashMap;
import java.util.Map;

import com.propertyservice.constants.AppConstants;
import com.propertyservice.dto.EmailRequest;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


@Configuration
public class KafkaProduceConfiguration {

    @Autowired
    private KafkaTemplate<String, EmailRequest> kafkaTemplate;


    @Bean
    public ProducerFactory<String, EmailRequest> producerFactory() {

        Map<String, Object> kafkaProps = new HashMap<>();

        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.KAFKA_HOST);
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(kafkaProps);
    }

    @Bean
    public KafkaTemplate<String, EmailRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}