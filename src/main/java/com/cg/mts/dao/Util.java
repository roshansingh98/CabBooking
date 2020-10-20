package com.cg.mts.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Util {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("cabbooking");
	EntityManager entityManager = factory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	private Util() {

	}

	public static EntityManager getEntityManager() {
		return new Util().entityManager;
	}

	public static EntityTransaction getTransaction() {
		return new Util().entityTransaction;
	}
}
