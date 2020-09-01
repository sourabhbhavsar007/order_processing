package com.op.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.demo.model.Order;
import com.op.demo.repository.OrderProcessingRepository;
import com.op.demo.utils.PaymentTypes;

@Service
public class OrderProcessingService {

	@Autowired
	private OrderProcessingRepository orderProcessingRepository;

	public void processOrder(Order order) {
		
		String paymentTypes = order.getPaymentTypes().toString();
		
		//Now based on payment types we would have conditional logic to process the order
		
		if(paymentTypes.equalsIgnoreCase(PaymentTypes.PHYSICAL_PRODUCT.toString())) {
			processPhysicalProduct(order);
			
		} else if(paymentTypes.equalsIgnoreCase(PaymentTypes.BOOK.toString())){
			processBook(order);
		
		} else if(paymentTypes.equalsIgnoreCase(PaymentTypes.ACTIVATE_MEMBERSHIP.toString())){
			activateMembership(order);
		
		} else if(paymentTypes.equalsIgnoreCase(PaymentTypes.UPGRADE_MEMBERSHIP.toString())){
			upgradeMembership(order);
		
		} else if(paymentTypes.equalsIgnoreCase(PaymentTypes.LEARNING_TO_SKI.toString())){
			processForLearningToSki(order);
		}
		
	}
	
	
	private void processPhysicalProduct(Order order) {
		// Add implementation logic for as per use case
		System.out.println("Action : Generate packing slip for shipping");
		System.out.println("Action : Generate commission payment for the agent");
		
		//Persist the changes
		//orderProcessingRepository.persistPhysicalProductOrder(order);
	}
	
	private void processBook(Order order) {
		// Use case implementation logic
		
		System.out.println("Action : Create duplicate packing slip for Royalty Department");
		System.out.println("Action : Generate commission payment for the agent");
		
	}
	

	private void activateMembership(Order order) {
		// Use case implementation logic
		
		System.out.println("Action : Activate the membership");
		System.out.println("Action : Email the owner about membership activation");
		
	}

	private void upgradeMembership(Order order) {
		// Use case implementation logic
		
		System.out.println("Action : Upgrade the membership");
		System.out.println("Action : Email the owner about membership upgradation");
	}

	private void processForLearningToSki(Order order) {
		// Use case implementation logic
		
		System.out.println("Action : Add free First Aid video to packing slip");
		
	}
	
}
