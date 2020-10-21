package com.cg.mts.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cg.mts.dao.TripBookingDao;
import com.cg.mts.repository.ITripBookingRepository;
import com.cg.mts.util.Util;
import com.cg.mts.entities.TripBooking;

public class TripBookingService implements ITripBookingService {

	private EntityManager em;

	private ITripBookingRepository tripBookingDao;

	public TripBookingService() {
		Util util = Util.getInstance();
		em = util.getEntityManager();
		tripBookingDao = new TripBookingDao(em);
	}

	public TripBooking insertTripBooking(TripBooking tripBooking) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		tripBooking = tripBookingDao.insertTripBooking(tripBooking);
		et.commit();
		return tripBooking;
	}

	public TripBooking updateTripBooking(TripBooking tripBooking) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		tripBooking = tripBookingDao.updateTripBooking(tripBooking);
		et.commit();
		return tripBooking;
	}

	public TripBooking deleteTripBooking(TripBooking tripBooking) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		tripBooking = tripBookingDao.deleteTripBooking(tripBooking);
		et.commit();
		return tripBooking;
	}

	public List<TripBooking> viewAllTripsCustomer(int customerId) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<TripBooking> viewAllTrips = tripBookingDao.viewAllTripsCustomer(customerId);
		et.commit();
		return viewAllTrips;
	}

	public TripBooking calculateBill(int customerId) {
//		TripBooking tripBooking = em.find(TripBooking.class, customerId);
		TripBooking tripBooking = tripBookingDao.calculateBill(customerId);
		return tripBooking;
	}
}
