package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDetailDTO;
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
	
	public UserDetailDTO getUserById(Long id){
		User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		return new UserDetailDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getName(), user.getImage(), user.getRole());
	}
	
	public User createUser(User userParam) {
		String userPassword = userParam.getPassword();
		PasswordEncoder encoder = new BCryptPasswordEncoder();;
		String encodedPassword = encoder.encode(userPassword);
		userParam.setPassword(encodedPassword);
		return this.userRepository.save(userParam); 
	}
	
//	public String deleteUser(Long id) {
//		Optional<User> user = this.getUserById(id);
//		if(user.isPresent()) {
//			this.userRepository.delete(user.get());
//			return "The user \"" + user.get().getUsername() + "\" has been deleted successfully";
//		}
//		else {
//			return "The user \"" + user.get().getUsername() + "\" has not been deleted successfully";
//		}
//	}
	
	public UserDetailDTO updateUser(Long id, User userParam) {
		User user = this.userRepository.findById(id).
				orElseThrow(() -> new RuntimeException("User not found"));
		
		user.setEmail(userParam.getEmail());
		user.setName(userParam.getName());
		user.setImage(userParam.getImage());
		
//		PasswordEncoder encoder = new BCryptPasswordEncoder();;
//		String encodedPassword = encoder.encode(userParam.getPassword());
//		user.setPassword(encodedPassword);
		
		this.userRepository.save(user);
		
		return new UserDetailDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getName(), user.getImage(), user.getRole());
		
	}
	
	public UserDetailDTO findByUsername(String username) {
		User user = userRepository.findByUsername(username)
		        .orElseThrow(() -> new RuntimeException("User not found"));
		
		return new UserDetailDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getName(), user.getImage(), user.getRole());
	}

}
