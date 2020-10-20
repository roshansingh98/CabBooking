package com.cg.mts.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cg.mts.dao.Util;
import com.cg.mts.entities.Driver;

public class DriverService implements IDriverService {


	private EntityManager em;
	EntityTransaction et;


	public DriverService() {
		Util util = Util.getInstance();
		em = util.getEntityManager();
		et = em.getTransaction();
	}

	public Driver insertDriver(Driver driver) {
		et.begin();
		em.persist(driver);
		et.commit();
		return driver;
	}

	public Driver updateDriver(Driver driver) {
		et.begin();
		Driver dr = em.merge(driver);
		et.commit();
		return dr;
	}

	public Driver deleteDriver(int driverId) {
		et.begin();
		Driver driver = em.find(Driver.class, driverId);
		em.remove(driver);
		et.commit();
		return driver;
	}

	public List<Driver> viewBestDrivers() {
		et.begin();
		List<Driver> bestDrivers = em.createQuery("select * from driver where rating >= 4.5", Driver.class).getResultList();
		et.commit();
		return bestDrivers;
	}

	public Driver viewDriver(int driverId) {
		et.begin();
		Driver driver = em.find(Driver.class, driverId);
		et.commit();
		return driver;
	}

}
