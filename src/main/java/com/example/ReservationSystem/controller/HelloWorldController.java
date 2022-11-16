package com.example.ReservationSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String hello() {
        return "hello! Today is " + LocalDateTime.now().toString();
    }
}
