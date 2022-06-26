package com.washapp.back.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	
	@RequestMapping("/")
    String hello() {
        return "Hello World, Spring Boot!";
    }
}
