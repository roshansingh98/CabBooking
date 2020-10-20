package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractUser {
	private String username;
	private String password;
	private String mobileNumber;
	private String email;
}