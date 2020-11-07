package com.cg.mts.repository;

import java.util.List;

import com.cg.mts.entities.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITripBookingRepository extends JpaRepository<TripBooking, Integer> {
	//	public TripBooking insertTripBooking(TripBooking tripBooking);
//	public TripBooking updateTripBooking(TripBooking tripBooking);
//	public TripBooking deleteTripBooking(TripBooking tripBooking);
//	public List<TripBooking> viewAllTripsCustomer(int customerId);
//	public TripBooking calculateBill(int customerId);

	public List<TripBooking> findByCustomerId(int customerId);
	public TripBooking findBillByCustomerId(int customerId);
}
