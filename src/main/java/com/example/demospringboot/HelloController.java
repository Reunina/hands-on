package com.example.demospringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/customPath")
    public String secondIndex() {
        return "Greetings from Spring Boot! (customPath)";
    }

}