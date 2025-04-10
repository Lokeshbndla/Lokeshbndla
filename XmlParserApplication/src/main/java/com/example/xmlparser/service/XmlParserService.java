package com.example.xmlparser.service;

import com.example.xmlparser.model.People;
import com.example.xmlparser.model.Person;
import com.example.xmlparser.repository.PersonRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

@Service
public class XmlParserService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void parseAndSavePeople(String fileName) {
        try {
            // Create JAXB context and unmarshaller
            JAXBContext context = JAXBContext.newInstance(People.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + fileName);
            }

            // Unmarshal XML to People object
            People people = (People) unmarshaller.unmarshal(inputStream);
            List<Person> personList = people.getPersonList();

            // Clear the current Hibernate session to avoid NonUniqueObjectException
            entityManager.clear(); // Clears the persistence context

            // Iterate through the list and merge each person entity
            for (Person person : personList) {
                entityManager.merge(person); // Merge each person entity, updating or inserting as needed
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
