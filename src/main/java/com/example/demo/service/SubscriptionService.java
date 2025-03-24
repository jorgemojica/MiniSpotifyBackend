package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	public SubscriptionService(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}
	
	public List<Subscription> getAllSubscriptions(){
		return this.subscriptionRepository.findAll();
	}
	
	public Optional<Subscription> getSubscriptionById(Long id){
		Optional<Subscription> subscriptionOpt = this.subscriptionRepository.findById(id);
		if(subscriptionOpt.isPresent()) {
			return subscriptionOpt;
		}
		else {
			return null;
		}
	}
	
	public Subscription createSubscription(Subscription subscription) {
		return this.subscriptionRepository.save(subscription);
	}
	
	public String deleteSubscription(Long id) {
		Optional<Subscription> subscriptionOpt = this.getSubscriptionById(id);
		if(subscriptionOpt.isPresent()) {
			this.subscriptionRepository.delete(subscriptionOpt.get());
			return "The subscription \"" + subscriptionOpt.get().getType() + "\" has been deleted successfully";
		}
		else {
			return "The subscription has not been deleted successfully";
		}
	}
	
	public Subscription updateSubscription(Long id, Subscription subscriptionParam) {
		Optional<Subscription> subscriptionOpt = this.getSubscriptionById(id);
		if(subscriptionOpt.isPresent()) {
			Subscription subscription = subscriptionOpt.get();
			subscription.setType(subscriptionParam.getType());
			return this.subscriptionRepository.save(subscription);
		}
		else {
			return null;
		}
	}

}
