package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer extends AbstractUser {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer customerId;

	public Customer(String username, String password, String mobileNumber, String email) {
		super(username, password, mobileNumber, email);
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
}