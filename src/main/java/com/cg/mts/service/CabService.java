package com.cg.mts.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cg.mts.dao.Util;
import com.cg.mts.entities.Cab;

public class CabService implements ICabService {

	private EntityManager em;
	EntityTransaction et;

	public CabService() {
		Util util = Util.getInstance();
		em = util.getEntityManager();
		et = em.getTransaction();
	}

	public Cab insertCab(Cab cab) {
		et.begin();
		em.persist(cab);
		et.commit();
		return cab;
	}

	public Cab updateCab(Cab cab) {
		et.begin();
		Cab cb = em.merge(cab);
		et.commit();
		return cb;
	}

	public Cab deleteCab(Cab cab) {
		et.begin();
		em.remove(cab);
		et.commit();
		return cab;
	}

	public List<Cab> viewCabsOfType(String carType) {
		et.begin();
		List<Cab> listOfCabs = em.createQuery("select * from cab where cartype = 'cartype'").setParameter("cartype", carType).getResultList();
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
