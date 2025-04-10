package com.example.xmlparser;

import com.example.xmlparser.service.XmlParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XmlParserApplication implements CommandLineRunner {

	@Autowired
	private XmlParserService xmlParserService;

	public static void main(String[] args) {
		SpringApplication.run(XmlParserApplication.class, args);
	}

	@Override
	public void run(String... args) {
		xmlParserService.parseAndSavePeople("person.xml");
		System.out.println("All people parsed and saved successfully.");
	}
}
