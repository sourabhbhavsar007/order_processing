package com.op.demo.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.op.demo.utils.PaymentTypes;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Data 
@Getter
@Setter //using lombok for constructors and getters/setters
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("id")
	private Long id;
	
	
	//We can @Many to One mappings, since order should be associated with a customer
	@Column
	@JsonProperty("customerId")
	private Long customerId;
	
	@Column
	@JsonProperty("description")
	private String description;
	
	@Column
	@JsonProperty("units")
	private int units;
	
	@Column
	@JsonProperty("price")
	private BigDecimal price;
	
	@Column
	@JsonProperty("paymentTypes")
	private PaymentTypes paymentTypes; // We use Enums, so we don't hardcode and it also becomes opens for extension.
									   // Since, any new category can easily be added => Open Closed Principle.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public PaymentTypes getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(PaymentTypes paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	
}
