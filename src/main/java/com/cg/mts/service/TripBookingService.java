package com.cg.mts.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cg.mts.util.Util;
import com.cg.mts.entities.TripBooking;

public class TripBookingService implements ITripBookingService {

	private EntityManager em;
	EntityTransaction et;

	public TripBookingService() {
		Util util = Util.getInstance();
		em = util.getEntityManager();
		et = em.getTransaction();
	}

	public TripBooking insertTripBooking(TripBooking tripBooking) {
		et.begin();
		em.persist(tripBooking);
		et.commit();
		return tripBooking;
	}

	public TripBooking updateTripBooking(TripBooking tripBooking) {
		et.begin();
		TripBooking trb = em.merge(tripBooking);
		et.commit();
		return trb;
	}

	public TripBooking deleteTripBooking(TripBooking tripBooking) {
		et.begin();
		em.remove(tripBooking);
		et.commit();
		return tripBooking;
	}

	public List<TripBooking> viewAllTripsCustomer(int customerId) {
		et.begin();
		List<TripBooking> trp = em.createQuery("Select * from tripbooking where customerid = 'customerid'", TripBooking.class).setParameter("customerid", customerId).getResultList();
		et.commit();
		return trp;
	}

	public TripBooking calculateBill(int customerId) {
		TripBooking tripBooking = em.find(TripBooking.class, customerId);
		return tripBooking;
	}

}
