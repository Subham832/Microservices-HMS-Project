package com.propertyservice.config; // Defines the package location for configuration-related classes in the Property Service module.

import java.util.HashMap; // Provides the HashMap implementation used to store Kafka producer properties.
import java.util.Map; // Provides the Map interface for key-value mapping.

import com.propertyservice.constants.AppConstants; // Imports application-wide constants, including Kafka host configuration.
import com.propertyservice.dto.EmailRequest; // Imports the EmailRequest DTO which will be serialized and sent via Kafka.
import org.apache.kafka.clients.producer.ProducerConfig; // Provides Kafka producer configuration constants.
import org.apache.kafka.common.serialization.StringSerializer; // Serializer for converting keys to string format.
import org.springframework.beans.factory.annotation.Autowired; // Enables automatic dependency injection of beans.
import org.springframework.context.annotation.Bean; // Indicates that the annotated method produces a Spring-managed bean.
import org.springframework.context.annotation.Configuration; // Marks the class as a source of bean definitions for the application context.
import org.springframework.kafka.core.DefaultKafkaProducerFactory; // Factory implementation for creating Kafka producers with default settings.
import org.springframework.kafka.core.KafkaTemplate; // Provides high-level operations to send messages to Kafka topics.
import org.springframework.kafka.core.ProducerFactory; // Interface for creating Kafka producer instances.
import org.springframework.kafka.support.serializer.JsonSerializer; // Serializer for converting Java objects to JSON for message value.

@Configuration // Declares this class as a configuration class for Spring to scan and register Kafka producer beans.
public class KafkaProduceConfiguration {

    @Autowired // Injects the KafkaTemplate bean wherever it is needed within the application.
    private KafkaTemplate<String, EmailRequest> kafkaTemplate;

    @Bean // Registers the returned ProducerFactory as a Spring bean.
    public ProducerFactory<String, EmailRequest> producerFactory() {
        Map<String, Object> kafkaProps = new HashMap<>(); // Creates a map to store Kafka producer configurations.

        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.KAFKA_HOST); // Sets the Kafka server address from application constants.
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Configures the key serializer to use String format.
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // Configures the value serializer to convert EmailRequest to JSON.

        return new DefaultKafkaProducerFactory<>(kafkaProps); // Returns a Kafka producer factory configured with the above properties.
    }

    @Bean // Registers the KafkaTemplate as a Spring bean for sending messages to Kafka.
    public KafkaTemplate<String, EmailRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory()); // Constructs and returns a KafkaTemplate using the configured producer factory.
    }
}
