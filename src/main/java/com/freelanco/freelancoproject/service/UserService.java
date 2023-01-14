package com.freelanco.freelancoproject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.freelanco.freelancoproject.User;
import com.freelanco.freelancoproject.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User getUser(int id) {
		return userRepository.findById(id).get();
	}
	
	public User getUser(String name) {
		return userRepository.findByUsername(name);
	}
	
	@Transactional
	public ResponseEntity<User> createNewUser(User newUser) {
		try {
			User user = userRepository.save(new User(newUser.getUsername(), 
					newUser.getPassword(), newUser.getEmail()));
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public ResponseEntity<User> updateUser(User user) {
		Optional<User> o_user = userRepository.findById(user.getUser_id());	// returns Optional<User>
		if (o_user.isPresent()) {
			User user1 = o_user.get();
			user1.setUsername(user.getUsername());
			user1.setEmail(user.getEmail());
			user1.setPassword(user.getPassword());
			
			return new ResponseEntity<User>(userRepository.save(user1), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
	}
	
	public ResponseEntity<HttpStatus> deleteUser(int id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			userRepository.deleteAll();
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
