package com.notificationserviceproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notificationserviceproject.constants.AppConstants;
import com.notificationserviceproject.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailRequestListner {

    @Autowired
    private JavaMailSender javaMailSender;

    @KafkaListener(topics = AppConstants.TOPIC, groupId = "group_email")
    public void kafakSubscriberContent(String emailRequest) {
//        System.out.print("_____________ Msg fecthed From Kafka_________________");
//        System.out.println(emailRequest);
        ObjectMapper mapper = new ObjectMapper();
        try {
            EmailRequest emailContent = mapper.readValue(emailRequest, EmailRequest.class);
//            System.out.println(emailContent.getTo());
//            System.out.println(emailContent.getSubject());
//            System.out.println(emailContent.getBody());
            SimpleMailMessage sm = new SimpleMailMessage();
            sm.setTo(emailContent.getTo());
            sm.setText(emailContent.getBody());

            javaMailSender.send(sm);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
