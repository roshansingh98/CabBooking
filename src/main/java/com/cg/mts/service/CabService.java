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
	private final EntityTransaction et;

	public CabService() {
		Util util = Util.getInstance();
		em = util.getEntityManager();
		et = em.getTransaction();
		cabDao = new CabDao(em);
	}

	public Cab insertCab(Cab cab) {
		et.begin();
		cab = cabDao.insertCab(cab);
		et.commit();
		return cab;
	}

	public Cab updateCab(Cab cab) {
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
		et.begin();
		int count = (Integer) em.createQuery("Select count(*) from cab where cartype = 'cartype'").setParameter("cartype", carType).getSingleResult();
		et.commit();
		return count;
	}

}
