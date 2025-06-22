package com.propertyservice.service; // Declares the package for service layer classes in the Property Service module.

import com.amazonaws.services.s3.AmazonS3; // AWS S3 client interface.
import com.amazonaws.services.s3.model.ObjectMetadata; // Metadata object to configure upload properties.
import org.springframework.beans.factory.annotation.Autowired; // Enables automatic dependency injection.
import org.springframework.beans.factory.annotation.Value; // Injects values from application properties.
import org.springframework.stereotype.Service; // Marks this class as a Spring service component.
import org.springframework.web.multipart.MultipartFile; // Represents uploaded files in a multipart request.

import java.io.IOException; // Handles input/output exceptions.
import java.util.ArrayList; // Used to create dynamic arrays.
import java.util.List; // List interface to store uploaded file URLs.
import java.util.UUID; // Generates unique file names.

/**
 * S3Service handles the business logic for uploading files to an AWS S3 bucket.
 * It supports uploading multiple MultipartFile instances and returns their public S3 URLs.
 */
@Service
public class S3Service {

    @Autowired
    private AmazonS3 amazonS3; // Injects the configured Amazon S3 client (defined in AmazonS3Config).

    @Value("${aws.s3.bucket-name}") // Reads the S3 bucket name from application properties.
    private String bucketName;

    /**
     * Uploads multiple files to AWS S3 and returns their publicly accessible URLs.
     *
     * @param files Array of MultipartFile objects received from the client.
     * @return List of public URLs pointing to the uploaded files in the S3 bucket.
     */
    public List<String> uploadFiles(MultipartFile[] files) {
        List<String> urls = new ArrayList<>(); // Stores the resulting file URLs.

        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); // Unique filename.

            try {
                ObjectMetadata metadata = new ObjectMetadata(); // Metadata for upload.
                metadata.setContentLength(file.getSize());

                amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata); // Upload to S3.

                String url = amazonS3.getUrl(bucketName, fileName).toString(); // Get public URL.
                urls.add(url);

            } catch (IOException e) {
                throw new RuntimeException("Error uploading file to S3", e); // Rethrow as unchecked exception.
            }
        }

        return urls; // Return list of public S3 URLs.
    }
}
