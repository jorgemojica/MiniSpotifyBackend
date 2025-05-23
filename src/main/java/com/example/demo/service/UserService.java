package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
	
	public Optional<User> getUserById(Long id){
		Optional<User> userOptional = this.userRepository.findById(id); 
		if(userOptional.isPresent()) {
			return userOptional;
		}
		else {
			return null;
		}
	}
	
	public User createUser(User userParam) {
		String userPassword = userParam.getPassword();
		PasswordEncoder encoder = new BCryptPasswordEncoder();;
		String encodedPassword = encoder.encode(userPassword);
		userParam.setPassword(encodedPassword);
		return this.userRepository.save(userParam); 
	}
	
	public String deleteUser(Long id) {
		Optional<User> user = this.getUserById(id);
		if(user.isPresent()) {
			this.userRepository.delete(user.get());
			return "The user \"" + user.get().getUsername() + "\" has been deleted successfully";
		}
		else {
			return "The user \"" + user.get().getUsername() + "\" has not been deleted successfully";
		}
	}
	
	public User updateUser(Long id, User userParam) {
		Optional<User> userOpt = this.userRepository.findById(id);
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			user.setEmail(userParam.getEmail());
			PasswordEncoder encoder = new BCryptPasswordEncoder();;
			String encodedPassword = encoder.encode(userParam.getPassword());
			user.setPassword(encodedPassword);
			return this.userRepository.save(user);
		}
		else {
			return null;
		}
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
		        .orElseThrow(() -> new RuntimeException("User not found"));
	}

}
