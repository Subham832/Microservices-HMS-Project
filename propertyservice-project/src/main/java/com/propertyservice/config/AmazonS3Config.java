package com.propertyservice.config; // Defines the package location for configuration-related classes in the Property Service module.

import com.amazonaws.auth.AWSStaticCredentialsProvider; // Provides static AWS credentials (access key and secret key).
import com.amazonaws.auth.BasicAWSCredentials; // Represents basic AWS credentials using access key and secret key.
import com.amazonaws.services.s3.AmazonS3; // Amazon S3 client interface used for interacting with the S3 service.
import com.amazonaws.services.s3.AmazonS3ClientBuilder; // Builder class for constructing the AmazonS3 client instance.
import org.springframework.beans.factory.annotation.Value; // Used to inject values from the application.properties or application.yml file.
import org.springframework.context.annotation.Bean; // Indicates that the annotated method produces a Spring-managed bean.
import org.springframework.context.annotation.Configuration; // Marks the class as a source of bean definitions for the application context.

@Configuration // Declares this class as a configuration class for Spring to scan and register bean definitions.
public class AmazonS3Config {

    @Value("${cloud.aws.region.static}") // Injects the AWS region configured in the application properties.
    private String region;

    @Value("${cloud.aws.credentials.access-key}") // Injects the AWS access key from the application properties.
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}") // Injects the AWS secret key from the application properties.
    private String secretKey;

    @Bean // Registers the returned AmazonS3 object as a Spring bean for dependency injection.
    public AmazonS3 amazonS3() {
        // Creates AWS credentials using the injected access key and secret key.
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        // Builds and returns an AmazonS3 client using the specified region and credentials provider.
        return AmazonS3ClientBuilder.standard()
                .withRegion(region) // Sets the AWS region for the client.
                .withCredentials(new AWSStaticCredentialsProvider(credentials)) // Supplies static credentials to the client.
                .build(); // Builds and returns the configured AmazonS3 client.
    }
}
