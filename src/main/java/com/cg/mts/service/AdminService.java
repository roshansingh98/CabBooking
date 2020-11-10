package com.cg.mts.service;


import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CabNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.exception.TripNotFoundException;
import com.cg.mts.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/* This annotation marks the class as a service class */
@Service
/*
 * @Transactional marks the transaction that we have to do in the methods while
 * updating the data
 */
@Transactional
// this is jpa repository only
public class AdminService implements IAdminService {

	/*
	 * IAdminRepository is a interface which extends JPA repository. We are trying
	 * to call the reference of it. Using @Autowired annotation , the object of the
	 * repository will be created during run time dynamicaly
	 */
	@Autowired
	private IAdminRepository adminRepository;

	/*
	 * this method will call adminRepository and will add the admin object using
	 * save method of the JPA repository
	 */
	@Override
	public Admin insertAdmin(Admin admin) {
		admin = adminRepository.save(admin);
		return admin;
	}

	/*
	 * this method will call adminRepository layer and will update the admin object
	 * for matching id. If however there is no matching id, we are returning a
	 * adminnotfound exception
	 */
	@Override
	public Admin updateAdmin(Admin admin) {
		boolean checkIfExists = adminRepository.existsById(admin.getAdminId());
		if (!checkIfExists) {
			throw new AdminNotFoundException("Adminn with admin id " + admin.getAdminId() + " does not exists");
		}
		admin = adminRepository.save(admin);
		return admin;
	}

	/*
	 * this method will call adminRepository and will delete the admin object for
	 * matching adminId. if however there is no matching id, we are returning
	 * AdminNotFoundException
	 */
	@Override
	public Admin deleteAdmin(int adminId) {
		Optional<Admin> adminOptional = adminRepository.findById(adminId);
		if (!adminOptional.isPresent()) {
			throw new AdminNotFoundException("No admin found with admin id as " + adminId);
		}
		Admin admin = adminOptional.get();
		adminRepository.delete(admin);
		return admin;

	}

	/*
	 * this method will return list of trip bookings of a customer with customer id.
	 * If no matching customer id found, we will return CustomerNotFound Exception
	 */
	@Override
	public List<TripBooking> getAllTrips(int customerId) {
		List<TripBooking> trips = null;
		trips = adminRepository.getAllTrips(customerId);
		if (trips.size() == 0) {
			throw new CustomerNotFoundException("No trips found with the customerid " + customerId);
		}
		return trips;
	}

	/*
	 * this method will return list of trip bookings based on cabType. if no trip
	 * booking found, we will return CabNotFoundException
	 */
	@Override
	public List<TripBooking> getTripsCabwise() {
		List<TripBooking> trips = adminRepository.getTripsCabwise();
		if (trips.size() == 0) {
			throw new CabNotFoundException("No cabs are there for the trips");
		}
		return trips;
	}

	/*
	 * this method will return list of trip booking grouped by customerId. if no
	 * trip booking found, we will return TripNotFoundException
	 */
	@Override
	public List<TripBooking> getTripsCustomerwise() {
		List<TripBooking> trips = adminRepository.getTripsCustomerwise();
		if (trips.size() == 0) {
			throw new TripNotFoundException("No trips per customer");
		}
		return trips;
	}

	/*
	 * this method will return list of trip booking grouped by dates. if no trip
	 * booking found, we will return tripNotFoundException
	 */
	@Override
	public List<TripBooking> getTripsDatewise() {
		List<TripBooking> trips = adminRepository.getTripsDatewise();
		if (trips.size() == 0) {
			throw new TripNotFoundException("No trips available date wise");
		}
		return trips;
	}

	/*
	 * this method will return list of trip booking for matching customer id, from
	 * Date and toDate. if no matching trip found, we can return customerNotFound
	 * Exception
	 */
	@Override
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		List<TripBooking> trips = adminRepository.getAllTripsForDays(customerId, fromDate, toDate);
		if (trips.size() == 0) {
			throw new CustomerNotFoundException("No Trip for customer id " + customerId);
		}
		return trips;
	}
}