package com.cg.mts.entities;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(query = "select c from Cab c where c.carType = :cartype", name = "Find cabs from cars"),
		@NamedQuery(query = "select count(c) from Cab c where c.carType = :cartype", name = "find cabs from cartype")
})
public class Cab {

	@GeneratedValue
	@Id
	private int cabId;
	private String carType;
	private float perKmRate;

	public Cab(int cabId, String carType, float perKmRate) {
		super();
		this.cabId = cabId;
		this.carType = carType;
		this.perKmRate = perKmRate;
	}

	public int getCabId() {
		return cabId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public float getPerKmRate() {
		return perKmRate;
	}

	public void setPerKmRate(float perKmRate) {
		this.perKmRate = perKmRate;
	}


}