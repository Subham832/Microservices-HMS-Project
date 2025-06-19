package com.propertyservice.service;
// Declares the package for service layer classes in the Property Service module.

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service // Indicates that this class is a Spring-managed service containing business logic.
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3;
    // Injects the Amazon S3 client configured in the application context (via AmazonS3Config).

    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    // Injects the S3 bucket name from application properties or YAML configuration.

    /**
     * Uploads multiple files to AWS S3 and returns their public URLs.
     *
     * @param files Array of MultipartFile objects received from the client.
     * @return List of public URLs pointing to the uploaded files in the S3 bucket.
     */
    public List<String> uploadFiles(MultipartFile[] files) {
        List<String> urls = new ArrayList<>(); // Stores the resulting file URLs.

        for (MultipartFile file : files) {
            // Generates a unique filename using UUID to prevent name collisions in the bucket.
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            try {
                // Prepares metadata including content length for the upload.
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(file.getSize());

                // Uploads the file to the specified S3 bucket.
                amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);

                // Generates and stores the accessible URL of the uploaded file.
                String url = amazonS3.getUrl(bucketName, fileName).toString();
                urls.add(url);

            } catch (IOException e) {
                // In case of an I/O error, throws a runtime exception with the root cause.
                throw new RuntimeException("Error uploading file to S3", e);
            }
        }
        return urls; // Returns the list of uploaded file URLs.
    }
}
