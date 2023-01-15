package com.freelanco.freelancoproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelanco.freelancoproject.User;
import com.freelanco.freelancoproject.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private int pincode;
	
	@GetMapping(value="{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<User> getUserById(@PathVariable int id) {
		return userService.getUser(id);
	}
	
	@GetMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<User> getUserByName(@RequestParam String name) {
		return userService.getUser(name);
	}
	
	@GetMapping(value="/all")
	private ResponseEntity<List<User>> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<User> createUser(@RequestBody User newUser) {
		return userService.createNewUser(newUser);
	}
	
	@PostMapping(value="/update", consumes=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<User> updateUser(@RequestBody User user) {
		System.out.println(user.toString());
		return userService.updateUser(user);
	}
	
	@DeleteMapping(value="/delete/{id}")
	private ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}
	
	@DeleteMapping(value="/deleteAllUsers")
	private ResponseEntity<HttpStatus> deleteAllUsers() {
		System.out.println(pincode);
		return userService.deleteAllUsers();
	}
	
	@GetMapping(value="/update", produces=MediaType.TEXT_PLAIN_VALUE)
	private String getUpdatePage() {
		return "We are at the update Page! Update user here.";
	}
}
