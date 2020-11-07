package com.cg.mts.entities;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="drivertable")
public class Driver extends AbstractUser {

	@Id
	@GeneratedValue
	private int driverId;
	private String licenseNo;
	@Autowired
	@OneToOne(cascade = { CascadeType.ALL })
	private Cab cab;
	private float rating;
	@OneToMany(mappedBy = "driver")
	private List<TripBooking> list;


	public Driver(String username, String password, String mobileNumber, String email, String licenseNo, Cab cab, float rating) {
		super(username, password, mobileNumber, email);
		this.licenseNo = licenseNo;
		this.cab = cab;
		this.rating = rating;
	}

	public Driver() {
		super();
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public int getDriverId() {
		return driverId;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public List<TripBooking> getList() {
		return list;
	}

	public void setList(List<TripBooking> list) {
		this.list = list;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Driver driver = (Driver) o;
		return Float.compare(driver.rating, rating) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rating);
	}

}