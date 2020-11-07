package com.cg.mts.entities;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name="tripsbooking")
@NamedQueries({
		@NamedQuery(query = "Select e from TripBooking e where e.customerId = :customerId", name = "find tripbooking by customerId"),
		@NamedQuery(query = "select e from TripBooking e where e.customerId = :customerId and e.fromDateTime = :fromdatetime and e.toDateTime = :todatetime", name = "find tripbooking by multiple")
})
public class TripBooking {
	@Id
	@GeneratedValue
	private int tripBookingId;
	private int customerId;
	@Autowired
	@ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name = "Driver")
	private Driver driver;
	private String fromLocation;
	private String toLocation;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private boolean status;
	private float distanceInKm;
	private float bill;

	public TripBooking() {
	}

	public TripBooking(int customerId, Driver driver, String fromLocation, String toLocation,
					   LocalDateTime fromDateTime, LocalDateTime toDateTime, boolean status, float distanceInKm, float bill) {
		super();
		this.customerId = customerId;
		this.driver = driver;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.status = status;
		this.distanceInKm = distanceInKm;
		this.bill = bill;
	}

	public void setTripBookingId(int tripBookingId) {
		this.tripBookingId = tripBookingId;
	}

	public int getTripBookingId() {
		return tripBookingId;
	}


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public LocalDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public float getDistanceInKm() {
		return distanceInKm;
	}

	public void setDistanceInKm(float distanceInKm) {
		this.distanceInKm = distanceInKm;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}

}
