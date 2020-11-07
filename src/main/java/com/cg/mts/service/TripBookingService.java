package com.cg.mts.service;

import java.util.List;

import com.cg.mts.exception.TripNotFoundException;
import com.cg.mts.repository.ITripBookingRepository;

import com.cg.mts.entities.TripBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TripBookingService implements ITripBookingService {

	@Autowired
	private ITripBookingRepository tripBookingRepository;

	@Override
	public TripBooking insertTripBooking(TripBooking tripBooking) {
		tripBooking = tripBookingRepository.save(tripBooking);
		return tripBooking;
	}

	@Override
	public TripBooking updateTripBooking(TripBooking tripBooking) {
		boolean checkIfExists = tripBookingRepository.existsById(tripBooking.getTripBookingId());
		if(!checkIfExists) {
			throw new TripNotFoundException("No Trip with trip booking id as " + tripBooking.getTripBookingId());
		}
		tripBooking = tripBookingRepository.save(tripBooking);
		return tripBooking;
	}

	@Override
	public TripBooking deleteTripBooking(TripBooking tripBooking) {
		boolean checkIfExists = tripBookingRepository.existsById(tripBooking.getTripBookingId());
		if(!checkIfExists) {
			throw new TripNotFoundException("No Trip with trip booking id as " + tripBooking.getTripBookingId());
		}
		tripBookingRepository.delete(tripBooking);
		return tripBooking;
	}

	@Override
	public List<TripBooking> viewAllTripsCustomer(int customerId) {
		List<TripBooking> trips = tripBookingRepository.findByCustomerId(customerId);
		if(trips.size() == 0) {
			throw new TripNotFoundException("No Trip with customer id " + customerId + " found");
		}
		return trips;
	}

	@Override
	public TripBooking calculateBill(int customerId) {
		TripBooking tripBooking = tripBookingRepository.findBillByCustomerId(customerId);
		if(tripBooking == null) {
			throw new TripNotFoundException("No trip bill found for the customer id "+ customerId);
		}
		return tripBooking;
	}
}
