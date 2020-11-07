package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admintable")
public class Admin extends AbstractUser {

	@GeneratedValue
	@Id
	private int adminId;


	public Admin(String username, String password, String mobileNumber, String email) {
		super(username, password, mobileNumber, email);
	}

	public Admin() {
		super();
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
}