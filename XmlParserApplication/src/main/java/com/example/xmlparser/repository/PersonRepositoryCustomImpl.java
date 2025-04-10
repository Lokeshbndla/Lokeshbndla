package com.example.xmlparser.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void clear() {
        entityManager.clear(); // Clears the persistence context
    }
}
