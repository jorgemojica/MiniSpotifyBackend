package com.example.demo.controller;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.DTO.AuthUserDTO;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.JwtService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthUserDTO authUserDTO) {
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authUserDTO.getUsername(), authUserDTO.getPassword()));
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(authUserDTO.getUsername());
			String token = jwtService.generateToken(userDetails);

			return ResponseEntity.ok(Collections.singletonMap("token", token));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(401).body("Invalid Credentials!");
		}
	}

}