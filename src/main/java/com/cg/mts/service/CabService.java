package com.cg.mts.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cg.mts.dao.CabDao;
import com.cg.mts.exception.CabNotFoundException;
import com.cg.mts.repository.ICabRepository;
import com.cg.mts.util.Util;
import com.cg.mts.entities.Cab;

public class CabService implements ICabService {

	private ICabRepository cabDao;

	private final EntityManager em;

	public CabService() {
		Util util = Util.getInstance();
		em = util.getEntityManager();
		cabDao = new CabDao(em);
	}

	public Cab insertCab(Cab cab) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		cab = cabDao.insertCab(cab);
		et.commit();
		return cab;
	}

	public Cab updateCab(Cab cab) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			cab = cabDao.updateCab(cab);
		} catch (CabNotFoundException e) {
			e.getMessage();
		}
		et.commit();
		return cab;
	}

	public Cab deleteCab(Cab cab) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			cab = cabDao.deleteCab(cab);
		} catch (CabNotFoundException e) {
			e.getMessage();
		}
		et.commit();
		return cab;
	}

	public List<Cab> viewCabsOfType(String carType) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<Cab> listOfCabs = null;
		try {
			listOfCabs = cabDao.viewCabsOfType(carType);
		} catch (CabNotFoundException e) {
			e.printStackTrace();
		}
		et.commit();
		return listOfCabs;
	}

	public int countCabsOfType(String carType) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		int count = (Integer) em.createQuery("Select count(*) from cab where cartype = 'cartype'").setParameter("cartype", carType).getSingleResult();
		et.commit();
		return count;
	}

}
