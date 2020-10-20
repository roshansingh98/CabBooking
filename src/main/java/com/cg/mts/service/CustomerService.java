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

//    EntityManager em = Util.getEntityManager();
//    EntityTransaction et = Util.getTransaction();

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
        return null;
    }

    @Override
    public Customer viewCustomer(int customerId) {
        return em.find(Customer.class, customerId);
    }

    @Override
    public Customer validateCustomer(String username, String password) {
        return null;
    }
}
