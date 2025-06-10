package com.microservice1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/message")
    public String getMessage(){
        return "Hello, World! This is Microservice 1.";
    }
}
