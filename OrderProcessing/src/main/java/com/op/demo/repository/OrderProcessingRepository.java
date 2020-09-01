package com.op.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.op.demo.model.Order;

@Repository
@Transactional
public interface OrderProcessingRepository extends JpaRepository<Order, Long>{

	//We can have persisting logic in this layer
	
	//@Query("Insert into Orders values (...)")
	//void persistPhysicalProductOrder(Order order);
	
	
}
