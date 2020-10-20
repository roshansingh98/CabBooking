package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cab {
	@Id
	private int cabId;
	private String carType;
	private float perKmRate;
}