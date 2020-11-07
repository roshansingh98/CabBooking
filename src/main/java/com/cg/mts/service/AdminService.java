package com.cg.mts.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import com.cg.mts.exception.*;
import com.cg.mts.repository.IAdminRepository;
import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CabNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.exception.InvalidAdminException;
import com.cg.mts.exception.InvalidCustomerException;
import com.cg.mts.exception.TripNotFoundException;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Autowired
	IAdminRepository adminRepository;

	@Override
	public Admin insertAdmin(Admin admin) {
		admin = adminRepository.save(admin);
		return admin;
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		boolean checkIfExists = adminRepository.existsById(admin.getAdminId());
		if(!checkIfExists) {
			throw new AdminNotFoundException("Admin with admin id " + admin.getAdminId() + " does not exists");
		}
		admin = adminRepository.save(admin);
		return admin;
	}

	@Override
	public Admin deleteAdmin(int adminId) {
		Optional<Admin> adminOptional = adminRepository.findById(adminId);
		if(!adminOptional.isPresent()) {
			throw new AdminNotFoundException("No admin found with admin id as "+adminId);
		}
		Admin admin = adminOptional.get();
		adminRepository.delete(admin);
		return admin;

	}

	@Override
	public List<TripBooking> getAllTrips(int customerId) {
		List<TripBooking> trips = null;
		trips = adminRepository.getAllTrips(customerId);
		if (trips.size()==0) {
			throw new CustomerNotFoundException("No trips found with the customerid " + customerId);
		}
		return trips;
	}

	@Override
	public List<TripBooking> getTripsCabwise() {
		List<TripBooking> trips = adminRepository.getTripsCabwise();
		if(trips.size() == 0) {
			throw new CabNotFoundException("No cabs are there for the trips");
		}
		return trips;
	}

	@Override
	public List<TripBooking> getTripsCustomerwise() {
		List<TripBooking> trips = adminRepository.getTripsCustomerwise();
		if(trips.size() == 0) {
			throw new TripNotFoundException("No trips per customer");
		}
		return trips;
	}

	@Override
	public List<TripBooking> getTripsDatewise() {
		List<TripBooking> trips = adminRepository.getTripsDatewise();
		if(trips.size() == 0) {
			throw new TripNotFoundException("No trips available date wise");
		}
		return trips;
	}

	@Override
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		List<TripBooking> trips = adminRepository.getAllTripsForDays(customerId, fromDate, toDate);
		if(trips.size() == 0) {
			throw new CustomerNotFoundException("No Trip for customer id " + customerId);
		}
		return trips;
	}


//	try {
//		if (admin.getEmail() == null) {
//			throw new InvalidCustomerException("Null values not accepted");
//		} else if (!Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", admin.getEmail())) {
//			throw new InvalidCustomerException("email should be of type abc@gmail.com");
//		} else if (!Pattern.matches("(0/91)?[7-9][0-9]{9}", admin.getMobileNumber())) {
//			throw new InvalidCustomerException("phone number is not valid. it should 9600XXXXX");
//		} else if (admin.getPassword().length() < 6) {
//			throw new InvalidCustomerException("password should be six characters or more");
//		} else if (admin.getUsername().length() < 6) {
//			throw new InvalidCustomerException("username should be six characters or more");
//		} else if (admin.getMobileNumber() == null) {
//			throw new InvalidCustomerException("Mobile Number cannot be null");
//		} else if (admin.getPassword() == null) {
//			throw new InvalidCustomerException("Password cannot be null");
//		} else if (admin.getUsername() == null) {
//			throw new InvalidCustomerException("Username cannot be null");
//		}
//	} catch (InvalidCustomerException e) {
//		System.out.println(e.getMessage());
//		return new Admin();
//	}

}