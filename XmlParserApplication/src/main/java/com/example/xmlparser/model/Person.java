package com.example.xmlparser.model;

import jakarta.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "person")
@XmlRootElement(name = "person")
public class Person {

    @Id
    private int id;
    private String name;
    private int age; // Added age field
    private String email;

    // Required by JAXB for XML parsing
    public Person() {}

    public int getId() {
        return id;
    }

    @XmlElement(name = "id") // XML element matches the case of the XML tags
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name") // XML element matches the case of the XML tags
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @XmlElement(name = "age") // XML element for age
    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement(name = "email") // XML element matches the case of the XML tags
    public void setEmail(String email) {
        this.email = email;
    }
}
