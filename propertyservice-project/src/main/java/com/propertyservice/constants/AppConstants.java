package com.propertyservice.constants; // Defines the package location for application-wide constant definitions.

/**
 * AppConstants holds constant values used across the Property Service application,
 * specifically for Kafka messaging configurations.
 */
public class AppConstants {

    public static final String TOPIC = "send-email"; // Kafka topic name used for sending email-related messages.
    public static final String KAFKA_HOST = "ec2-13-201-80-170.ap-south-1.compute.amazonaws.com:9092"; // Kafka broker host address and port.

}
