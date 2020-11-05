package com.cg.mts.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class PersistanceUtil {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("cabbooking");

	private Set<EntityManager> entityManagers = new HashSet<>();

	private static PersistanceUtil instance = new PersistanceUtil();

	public static PersistanceUtil getInstance() {
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
}