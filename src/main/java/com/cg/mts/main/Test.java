package com.cg.mts.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.cg.mts.entities.AbstractUser;
import com.cg.mts.entities.Customer;
import com.cg.mts.service.*;

public class Test {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("cabbooking");
	EntityManager entityManager = factory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public static void main(String[] args) {

		Test test = new Test();
		Customer customer = new Customer("Customer", "123", "123456", "abc");
//		test.beginTransaction();
//		test.insert(customer);
//		test.endTransaction();
//		test.beginTransaction();
//		Customer retrieved = test.retrieve(customer);
//		test.endTransaction();
//		System.out.println(retrieved.getCustomerId());

		ICustomerService customerService = new CustomerService();
		Customer c = customerService.viewCustomer(1);
		System.out.println("New customer has" + c);
	}

	public void beginTransaction() {
		entityTransaction.begin();
	}

	public void insert(AbstractUser customer) {
		entityManager.persist(customer);
		System.out.println("Customer Inserted Successfully");
	}

	//	public Customer retrieve(Customer customer) {
//		return entityManager.find(customer.getClass(), 1);
//	}
//
	public void endTransaction() {
		entityTransaction.commit();
	}
//	
//	public List<TripBooking> getAll(){
//		List<TripBooking> li = entityManager.createQuery("SELECT tr FROM tripbooking tr WHERE tr.customerId = 'customerID'").setParameter("customerID", 1).getResultList();
//		return li;
//	}
}
