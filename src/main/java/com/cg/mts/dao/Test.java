package com.cg.mts.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cg.mts.entities.AbstractUser;
import com.cg.mts.entities.Customer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("student");
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		AbstractUser student = new Customer(124);
		entityManager.persist(student);
		entityTransaction.commit();
		System.out.println("Customer Inserted Successfully");

	}

}
