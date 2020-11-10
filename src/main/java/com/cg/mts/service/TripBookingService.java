package com.cg.mts.service;

import com.cg.mts.entities.TripBooking;
import com.cg.mts.exception.TripNotFoundException;
import com.cg.mts.repository.ITripBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/* This annotation marks the class as a service class */
@Service
/*
 * @Transactional marks the transaction that we have to do in the methods while
 * updating the data
 */
@Transactional
public class TripBookingService implements ITripBookingService {

	/*
	 * ITripBookingRepository is a interface which extends JPA repository. We are
	 * trying to call the reference of it. Using @Autowired annotation , the object
	 * of the repository will be created during run time dynamicaly
	 */
	@Autowired
	private ITripBookingRepository tripBookingRepository;

	/*
	 * this method is adding the trip booking object to the database using save
	 * method of JPA repository
	 */
	@Override
	public TripBooking insertTripBooking(TripBooking tripBooking) {
		tripBooking = tripBookingRepository.save(tripBooking);
		return tripBooking;
	}

	/*
	 * this method is updating the trip booking object to the database with matching
	 * id. if there is no matching id, we will return a tripnot found exception
	 */
	@Override
	public TripBooking updateTripBooking(TripBooking tripBooking) {
		boolean checkIfExists = tripBookingRepository.existsById(tripBooking.getTripBookingId());
		if (!checkIfExists) {
			throw new TripNotFoundException("No Trip with trip booking id as " + tripBooking.getTripBookingId());
		}
		tripBooking = tripBookingRepository.save(tripBooking);
		return tripBooking;
	}

	/*
	 * this method will delete the trip booking object from database. if there is no
	 * matching id, we will return trip not found exception
	 */
	@Override
	public TripBooking deleteTripBooking(TripBooking tripBooking) {
		boolean checkIfExists = tripBookingRepository.existsById(tripBooking.getTripBookingId());
		if (!checkIfExists) {
			throw new TripNotFoundException("No Trip with trip booking id as " + tripBooking.getTripBookingId());
		}
		tripBookingRepository.delete(tripBooking);
		return tripBooking;
	}

	/*
	 * this method will return list of trip booking with matching customer id. if
	 * there is no matching trips, we will return tripnotfound exception
	 */
	@Override
	public List<TripBooking> viewAllTripsCustomer(int customerId) {
		List<TripBooking> trips = tripBookingRepository.findByCustomerId(customerId);
		if (trips.size() == 0) {
			throw new TripNotFoundException("No Trip with customer id " + customerId + " found");
		}
		return trips;
	}

	/*
	 * this method will return the bill of the trip based on a customer id. if there
	 * is no matching id, it will say no trio found exception
	 */
	@Override
	public TripBooking calculateBill(int customerId) {
		TripBooking tripBooking = tripBookingRepository.findBillByCustomerId(customerId);
		if (tripBooking == null) {
			throw new TripNotFoundException("No trip bill found for the customer id " + customerId);
		}
		return tripBooking;
	}
}
