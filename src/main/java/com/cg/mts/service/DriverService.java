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


	private EntityManager entityManager;

	private final IDriverRepository driverDao;

	public DriverService() {
		Util util = Util.getInstance();
		entityManager = util.getEntityManager();
		driverDao = new DriverDao(entityManager);
	}

	public Driver insertDriver(Driver driver) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		driver = driverDao.insertDriver(driver);
		entityTransaction.commit();
		return driver;
	}

	public Driver updateDriver(Driver driver) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			driver = driverDao.updateDriver(driver);
		} catch (DriverNotFoundException e) {
			System.out.println(e.getMessage());
		}
		entityTransaction.commit();
		return driver;
	}

	public Driver deleteDriver(int driverId) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Driver driver = null;
		try {
			driver = driverDao.deleteDriver(driverId);
		} catch (DriverNotFoundException e) {
			System.out.println(e.getMessage());
		}
		entityTransaction.commit();
		return driver;
	}

	public List<Driver> viewBestDrivers() {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		List<Driver> bestDrivers = null;
		try {
			bestDrivers = driverDao.viewBestDrivers();
		} catch (DriverNotFoundException e) {
			System.out.println(e.getMessage());
		}
		entityTransaction.commit();
		return bestDrivers;
	}

	public Driver viewDriver(int driverId) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Driver driver = null;
		try {
			driver = driverDao.viewDriver(driverId);
		} catch (DriverNotFoundException e) {
			System.out.println(e.getMessage());
		}
		entityTransaction.commit();
		return driver;
	}

}
