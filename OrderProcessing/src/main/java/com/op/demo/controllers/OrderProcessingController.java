package com.op.demo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.op.demo.model.Order;
import com.op.demo.service.OrderProcessingService;

@RestController
public class OrderProcessingController {
	
	@Autowired
	private OrderProcessingService orderProcessingService;
	

	@PostMapping("/api/v1/processOrder")
	public ResponseEntity<Object> processOrder(@RequestBody Order order) {
		//We will get Order details in RequestBody object i.e Request Payload in JSON format
		// Since we have an Order class which is model/POJO where we deserialize/parse RequestBody object and process it using jackson api
		// Model class has @JsonProperty annotations which helps doing so, by mapping json payload attributes to class attributes.
		
		//Assuming here we parse and have Order object populated with all the details, we forward it to process
		
		orderProcessingService.processOrder(order);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(order.getId()).toUri();

		return ResponseEntity.created(location).build();
		
	}
}
