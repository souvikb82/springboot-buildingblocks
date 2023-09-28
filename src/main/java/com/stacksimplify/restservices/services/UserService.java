package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException{
		
		if(userRepository.findByUsername(user.getUsername()) != null)
			throw new UserExistsException("User already exists in User Repository, use a different user name");
		
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long userId) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userId);
		
		if(!user.isPresent())
			throw new UserNotFoundException("User not found in User Repository");
		
		return user;
	}
	
	public User updateUserById(Long userId, User user) throws UserNotFoundException {
		Optional<User> usr = userRepository.findById(userId);
		
		if(!usr.isPresent())
			throw new UserNotFoundException("User not found in User Repository");
		
		user.setId(userId);
		return userRepository.save(user);
	}
	
	public void delateUserById(Long userId) {
		if(!userRepository.findById(userId).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in User Repository");
		}
		
		userRepository.deleteById(userId);
	}
	
	public User getUserByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}
}
