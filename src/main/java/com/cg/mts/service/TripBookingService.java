package com.cg.mts.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cg.mts.dao.TripBookingDao;
import com.cg.mts.repository.ITripBookingRepository;
import com.cg.mts.util.PersistanceUtil;
import com.cg.mts.entities.TripBooking;

public class TripBookingService implements ITripBookingService {

	private EntityManager entityManager;

	private ITripBookingRepository tripBookingDao;

	public TripBookingService() {
		PersistanceUtil persistanceUtil = PersistanceUtil.getInstance();
		entityManager = persistanceUtil.getEntityManager();
		tripBookingDao = new TripBookingDao(entityManager);
	}

	public TripBooking insertTripBooking(TripBooking tripBooking) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		tripBooking = tripBookingDao.insertTripBooking(tripBooking);
		entityTransaction.commit();
		return tripBooking;
	}

	public TripBooking updateTripBooking(TripBooking tripBooking) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		tripBooking = tripBookingDao.updateTripBooking(tripBooking);
		entityTransaction.commit();
		return tripBooking;
	}

	public TripBooking deleteTripBooking(TripBooking tripBooking) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		tripBooking = tripBookingDao.deleteTripBooking(tripBooking);
		entityTransaction.commit();
		return tripBooking;
	}

	public List<TripBooking> viewAllTripsCustomer(int customerId) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		List<TripBooking> viewAllTrips = tripBookingDao.viewAllTripsCustomer(customerId);
		entityTransaction.commit();
		return viewAllTrips;
	}

	public TripBooking calculateBill(int customerId) {
//		TripBooking tripBooking = em.find(TripBooking.class, customerId);
		TripBooking tripBooking = tripBookingDao.calculateBill(customerId);
		return tripBooking;
	}
}
