package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class PersonController {

    private final List<String> themes = Arrays.asList("light", "dark", "blue", "solarized");

    @GetMapping("/api/message")
    public Map<String, String> getMessageWithTheme() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "🌈 Welcome to Themed Spring Boot!");
        
        String theme = themes.get(new Random().nextInt(themes.size()));
        response.put("theme", theme);

        return response;
    }
}
