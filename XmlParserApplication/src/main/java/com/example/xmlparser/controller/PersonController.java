package com.example.xmlparser.controller;


import com.example.xmlparser.model.Person;
import com.example.xmlparser.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/persons") // Base path for all person-related endpoints
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // Endpoint to get all persons from the database
    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll(); // Fetches all persons from the database
    }
}
