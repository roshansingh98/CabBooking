package com.cg.mts.entities;

import javax.persistence.*;

@Entity
@NamedQuery(query = "select c from Customer c where c.username = :Username and c.password = :Password", name = "find customer by username and password")
public class Customer extends AbstractUser {

	@GeneratedValue
	@Id
	private Integer customerId;

	public Customer(String username, String password, String mobileNumber, String email) {
		super(username, password, mobileNumber, email);
	}

	public Customer() {

	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
}