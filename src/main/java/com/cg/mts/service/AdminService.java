package com.cg.mts.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.List;
import com.cg.mts.dao.Util;
import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;

public class AdminService implements IAdminService {

	private final EntityManager em;
	private final EntityTransaction et;

	public AdminService() {
		Util util = Util.getInstance();
		em = util.getEntityManager();
		et = em.getTransaction();
	}

	public Admin insertAdmin(Admin admin) {
		et.begin();
		em.persist(admin);
		et.commit();
		return admin;
	}

	public Admin updateAdmin(Admin admin) {
		et.begin();
		Admin ad = em.merge(admin);
		et.commit();
		return ad;
	}

	public Admin deleteAdmin(int adminId) {
		et.begin();
		Admin admin = em.find(Admin.class, adminId);
		em.remove(admin);
		et.commit();
		return admin;
	}

	public List<TripBooking> getAllTrips(int customerId) {
		et.begin();
		List<TripBooking> li = em.createQuery("SELECT * FROM tripbooking tr WHERE tr.customerId = 'customerID'", TripBooking.class).setParameter("customerID", customerId).getResultList();
		et.commit();
		return li;
	}

	public List<TripBooking> getTripsCabwise() {
		et.begin();
		List<TripBooking> li = em.createQuery("SELECT * FROM tripbooking tr WHERE driver_driverid in (select driverid from driver where cab_cabid in (select cabid from cab group by cabid order by cabid))", TripBooking.class).getResultList();
		et.commit();
		return li;
	}

	public List<TripBooking> getTripsCustomerwise() {
		et.begin();
		List<TripBooking> li = em.createQuery("SELECT * from tripbooking tr where customerid in (select customerid from customer group by customerid order by customerid)", TripBooking.class).getResultList();
		et.commit();
		return li;
	}

	public List<TripBooking> getTripsDatewise() {
		et.begin();
		List<TripBooking> li = em.createQuery("select tr from tripbooking tr group by fromdatetime", TripBooking.class).getResultList();
		et.commit();
		return li;
	}

	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		et.begin();
		List<TripBooking> li = em.createQuery("Select tr from tripbooking tr where customerId = 'customerID' and fromdatetime = 'fromdatetime' and todatetime = 'todatetime'", TripBooking.class).getResultList();
		et.commit();
		return li;
	}

}