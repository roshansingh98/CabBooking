package com.cg.mts.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cg.mts.dao.DriverDao;
import com.cg.mts.exception.DriverNotFoundException;
import com.cg.mts.repository.IDriverRepository;
import com.cg.mts.util.Util;
import com.cg.mts.entities.Driver;

public class DriverService implements IDriverService {


	private EntityManager em;

	private final IDriverRepository driverDao;

	public DriverService() {
		Util util = Util.getInstance();
		em = util.getEntityManager();
		driverDao = new DriverDao(em);
	}

	public Driver insertDriver(Driver driver) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		driver = driverDao.insertDriver(driver);
		et.commit();
		return driver;
	}

	public Driver updateDriver(Driver driver) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			driver = driverDao.updateDriver(driver);
		} catch (DriverNotFoundException e) {
			e.getMessage();
		}
		et.commit();
		return driver;
	}

	public Driver deleteDriver(int driverId) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		Driver driver = null;
		try {
			driver = driverDao.deleteDriver(driverId);
		} catch (DriverNotFoundException e) {
			e.getMessage();
		}
		et.commit();
		return driver;
	}

	public List<Driver> viewBestDrivers() {
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<Driver> bestDrivers = null;
		try {
			bestDrivers = driverDao.viewBestDrivers();
		} catch (DriverNotFoundException e) {
			e.getMessage();
		}
		et.commit();
		return bestDrivers;
	}

	public Driver viewDriver(int driverId) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		Driver driver = null;
		try {
			driver = driverDao.viewDriver(driverId);
		} catch (DriverNotFoundException e) {
			e.printStackTrace();
		}
		et.commit();
		return driver;
	}

}
