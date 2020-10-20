package com.cg.mts.service;

import com.cg.mts.dao.Util;
import com.cg.mts.entities.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CustomerService implements ICustomerService {

    private EntityManager em;
    EntityTransaction et;

    public CustomerService() {
        Util util = Util.getInstance();
        em = util.getEntityManager();
        et = em.getTransaction();
    }

    @Override
    public Customer insertCustomer(Customer customer) {
        et.begin();
        em.persist(customer);
        et.commit();
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        et.begin();
        em.merge(customer);
        return null;
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        et.begin();
        em.remove(customer);
        et.commit();
        return customer;
    }

    @Override
    public List<Customer> viewCustomers() {
        et.begin();
        List<Customer> customers = em.createQuery("Select * from customer", Customer.class).getResultList();
        et.commit();
        return customers;
    }

    @Override
    public Customer viewCustomer(int customerId) {
        return em.find(Customer.class, customerId);
    }

    @Override
    public Customer validateCustomer(String username, String password) {
        Customer customer = em.find(Customer.class, username);
        if (customer == null) {
            return null;
        } else {
            customer = em.find(Customer.class, password);
            if (customer == null) {
                return null;
            }
        }
        return customer;
    }
}
