package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer extends AbstractUser {
	@Id
	private int customerId;

	public Customer() {
		
	}
	public Customer(int customerId) {
		super();
		this.customerId = customerId;
	}
	
}
