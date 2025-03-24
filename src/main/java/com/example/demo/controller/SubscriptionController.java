package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;

@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	public SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}
	
	@GetMapping
	public List<Subscription> getAllSubscriptions(){
		return this.subscriptionService.getAllSubscriptions();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Subscription> getSubscription(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.subscriptionService.getSubscriptionById(id).get());
	}
	
	@PostMapping
	public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription){
		return ResponseEntity.ok(this.subscriptionService.createSubscription(subscription));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteSubscription(@PathVariable("id") Long id){
		return ResponseEntity.ok(this.subscriptionService.deleteSubscription(id));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Subscription> updateSubscription(@PathVariable("id") Long id, @RequestBody Subscription subscriptionParam){
		return ResponseEntity.ok(this.subscriptionService.updateSubscription(id, subscriptionParam));
	}

}
