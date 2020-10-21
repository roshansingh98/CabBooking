package com.cg.mts.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.List;

import com.cg.mts.dao.AdminDao;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CabNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.IAdminRepository;
import com.cg.mts.util.Util;
import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;

public class AdminService implements IAdminService {

	private IAdminRepository adminDao;

	private final EntityManager em;

	public AdminService() {
		Util util = Util.getInstance();
		em = util.getEntityManager();
		adminDao = new AdminDao(em);
	}

	public Admin insertAdmin(Admin admin) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		admin = adminDao.insertAdmin(admin);
		et.commit();
		return admin;
	}

	public Admin updateAdmin(Admin admin) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			admin = adminDao.updateAdmin(admin);
		} catch (AdminNotFoundException e) {
			e.getMessage();
		}
		et.commit();
		return admin;
	}

	public Admin deleteAdmin(int adminId) {
		EntityTransaction et = em.getTransaction();
		Admin admin = null;
		et.begin();
		try {
			admin = adminDao.deleteAdmin(adminId);
		} catch (AdminNotFoundException e) {
			e.getMessage();
		}
		et.commit();
		return admin;
	}

	public List<TripBooking> getAllTrips(int customerId) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<TripBooking> li = null;
		try {
			li = adminDao.getAllTrips(customerId);
		} catch (CustomerNotFoundException e) {
			e.getMessage();
		}
		et.commit();
		return li;
	}

	public List<TripBooking> getTripsCabwise() {
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<TripBooking> li = null;
		try {
			li = adminDao.getTripsCabwise();
		} catch (CabNotFoundException e) {
			e.getMessage();
		}
		et.commit();
		return li;
	}

	public List<TripBooking> getTripsCustomerwise() {
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<TripBooking> li = adminDao.getTripsCustomerwise();
		et.commit();
		return li;
	}

	public List<TripBooking> getTripsDatewise() {
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<TripBooking> li = adminDao.getTripsDatewise();
		et.commit();
		return li;
	}

	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<TripBooking> li = null;
		try {
			li = adminDao.getAllTripsForDays(customerId, fromDate, toDate);
		} catch (CustomerNotFoundException e) {
			e.getMessage();
		}
		et.commit();
		return li;
	}

}