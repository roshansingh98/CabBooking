package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin extends AbstractUser {
	@Id
	private int adminId;
}