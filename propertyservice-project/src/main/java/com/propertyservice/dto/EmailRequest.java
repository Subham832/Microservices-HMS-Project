package com.propertyservice.dto; // Defines the package location for Data Transfer Objects (DTOs).

/**
 * EmailRequest represents the payload structure used for sending emails.
 * It includes recipient address, subject line, and body content.
 */
public class EmailRequest {

    private String to; // Email recipient address.
    private String subject; // Subject of the email.
    private String body; // Body content of the email.

    public EmailRequest(String to, String subject, String body) { // Parameterized constructor.
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public EmailRequest() { // Default no-args constructor.
        super();
    }

    public String getTo() { // Getter for recipient address.
        return to;
    }

    public String getSubject() { // Getter for email subject.
        return subject;
    }

    public String getBody() { // Getter for email body.
        return body;
    }

    public void setTo(String to) { // Setter for recipient address.
        this.to = to;
    }

    public void setSubject(String subject) { // Setter for email subject.
        this.subject = subject;
    }

    public void setBody(String body) { // Setter for email body.
        this.body = body;
    }

    @Override
    public String toString() { // Returns a string representation of the EmailRequest object.
        return "EmailRequest{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
