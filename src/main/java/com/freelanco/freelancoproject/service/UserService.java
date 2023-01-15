package com.freelanco.freelancoproject.service;

import java.util.ArrayList;
import java.util.List;
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
	
	public ResponseEntity<User> getUser(int id) {
		Optional<User> o_user = userRepository.findById(id);
		
		if (o_user.isPresent()) {
			return new ResponseEntity<User>(o_user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<User> getUser(String name) {
		Optional<User> o_user = userRepository.findByUsername(name);
		
		if (o_user.isPresent()) {
			return new ResponseEntity<User>(o_user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> users = new ArrayList<User>();
			
			userRepository.findAll().forEach(users::add);
			
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
	
	@Transactional
	public ResponseEntity<HttpStatus> deleteUser(int id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			userRepository.deleteAll();
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
