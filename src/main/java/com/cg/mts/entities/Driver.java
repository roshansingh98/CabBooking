package com.cg.mts.entities;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Driver extends AbstractUser {
	@Id
	private int driverId;
	private String licenseNo;
	@OneToOne
	private Cab cab;
	private float rating;
	@OneToMany(mappedBy = "driver")
	private List<TripBooking> list;
}
