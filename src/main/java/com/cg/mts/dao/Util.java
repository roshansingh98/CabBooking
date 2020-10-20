package com.cg.mts.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class Util {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("cabbooking");

	private Set<EntityManager> entityManagers = new HashSet<>();

	private static Util instance = new Util();

	public static Util getInstance() {
		return instance;
	}

	public EntityManager getEntityManager() {
		EntityManager entityManager = factory.createEntityManager();
		entityManagers.add(entityManager);
		return entityManager;
	}

	public void close() {
		for (EntityManager manager : entityManagers) {
			manager.close();
		}
		factory.close();
	}

//	EntityManager entityManager = factory.createEntityManager();
//	EntityTransaction entityTransaction = entityManager.getTransaction();
//
//	private Util() {
//
//	}
//
//	public static EntityManager getEntityManager() {
//		return new Util().entityManager;
//	}
//
//	public static EntityTransaction getTransaction() {
//		return new Util().entityTransaction;
//	}
}