package com.freelanco.freelancoproject;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import com.freelanco.freelancoproject.repository.UserRepository;

@SpringBootApplication()
public class FreelancoProjectApplication implements CommandLineRunner {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(FreelancoProjectApplication.class, args);
	}
	
	@Bean
	public int pincode() {
		return 12345;
	}
	
	@Override
	public void run(String... args) throws Exception {
//		String sql = "INSERT INTO users (username, password, email) VALUES ("
//				+ "'shivam17arora', 'admin', 'arora17@sexymale.com')";
//		
//		int rows = jdbcTemplate.update(sql);
//		if (rows > 0) {
//			System.out.println("A new row has been inserted");
//		}
		
		List<User> users = userRepo.findAll();
		users.forEach(System.out::println);
	}

}
