package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.UserDetailDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getAllUsers(){
		return this.userService.getAllUsers();
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<UserDetailDTO> getUserById(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.userService.getUserById(id));
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<User> createUser(@RequestBody User user){
		return ResponseEntity.ok(this.userService.createUser(user));
	}
	
//	@DeleteMapping(value = "{id}")
//	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
//		return ResponseEntity.ok(this.userService.deleteUser(id));
//	}
	
	@PatchMapping(value = "{id}")
	public ResponseEntity<UserDetailDTO> updateUser(@PathVariable("id") Long id, @RequestBody User userParam){
		return ResponseEntity.ok(this.userService.updateUser(id, userParam));
	}
	
	@GetMapping(value = "/username/{username}")
	public ResponseEntity<UserDetailDTO> findByUsername(@PathVariable("username") String username){
		return ResponseEntity.ok(this.userService.findByUsername(username));
	}

}
