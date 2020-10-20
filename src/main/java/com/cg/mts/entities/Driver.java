package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Driver extends AbstractUser {
	@Id
	private int driverId;
	private String licenseNo;
	@OneToOne
	private Cab cab;
	private float rating;
	@OneToMany
	List<TripBooking> list = new ArrayList<TripBooking>();
}
