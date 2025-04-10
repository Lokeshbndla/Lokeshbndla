package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getUsersByName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	public List<User> getUsersByLastName(String lastName) {
		return userRepository.findByLastName(lastName);

	}
}