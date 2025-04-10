package com.example.xmlparser.repository;

import com.example.xmlparser.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {
    // You can define other query methods if needed
}
