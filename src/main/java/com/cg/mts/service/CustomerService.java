package com.cg.mts.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cg.mts.dao.Util;
import com.cg.mts.entities.Customer;

public class CustomerService implements ICustomerService {

	EntityManager em = Util.getEntityManager();
	EntityTransaction et = Util.getTransaction();
	public Customer insertCustomer(Customer customer) {
		et.begin();
		em.persist(customer);
		et.commit();
		return customer;
	}

	public Customer updateCustomer(Customer customer) {
		et.begin();
		em.merge(customer);
		et.commit();
		return customer;
	}

	public Customer deleteCustomer(Customer customer) {
		et.begin();
		em.remove(customer);
		et.commit();
		return customer;
	}

	public List<Customer> viewCustomers() {
		et.begin();
		List<Customer> customers = em.createQuery("Select * from customer").getResultList();
		et.commit();
		return customers;
	}

	public Customer viewCustomer(int customerId) {
		et.begin();
		Customer customer = em.find(Customer.class, customerId);
		et.commit();
		return customer;
	}

	public Customer validateCustomer(String username, String password) {
		Customer customer = em.find(Customer.class, username);
		if(customer == null) {
			return null;
		}
		else {
			customer = em.find(Customer.class, password);
			if(customer == null) {
				return null;
			}
		}
		return customer;
	}

}
