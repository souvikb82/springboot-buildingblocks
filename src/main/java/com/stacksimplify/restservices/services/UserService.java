package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long userId) {
		return userRepository.findById(userId);
	}
	
	public User updateUserById(Long userId, User user) {
		user.setId(userId);
		return userRepository.save(user);
	}
	
	public Boolean delateUserById(Long userId) {
		Boolean success = false;
		
		if(userRepository.findById(userId).isPresent()) {
			userRepository.deleteById(userId);
			success = true;
		}
		return success;
	}
	
	public User getUserByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}
}
