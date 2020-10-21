package com.cg.mts.service;

import com.cg.mts.dao.CustomerDao;
import com.cg.mts.util.Util;
import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CustomerService implements ICustomerService {

    private ICustomerRepository customerDao;

    private EntityManager em;

    public CustomerService() {
        Util util = Util.getInstance();
        em = util.getEntityManager();
        customerDao = new CustomerDao(em);
    }

    @Override
    public Customer insertCustomer(Customer customer) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        customer = customerDao.insertCustomer(customer);
        et.commit();
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {
            customer = customerDao.updateCustomer(customer);
        } catch (CustomerNotFoundException e) {
            e.getMessage();
        }

        et.commit();

        return customer;
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        EntityTransaction et = em.getTransaction();
        et.begin();

        try {
            customer = customerDao.deleteCustomer(customer);
        } catch (CustomerNotFoundException e) {
            e.getMessage();
        }

        et.commit();

        return customer;
    }

    @Override
    public List<Customer> viewCustomers() {
        EntityTransaction et = em.getTransaction();
        et.begin();
        List<Customer> customers = null;
        try {
            customers = customerDao.viewCustomers();
        } catch (CustomerNotFoundException e) {
            e.getMessage();
        }
        et.commit();
        return customers;
    }

    @Override
    public Customer viewCustomer(int customerId) {
        Customer customer = null;
        try {
            customer = customerDao.viewCustomer(customerId);
        } catch (CustomerNotFoundException e) {
            e.getMessage();
        }
        ;
        return customer;
    }

    @Override
    public Customer validateCustomer(String username, String password) {
        Customer customer = null;
        try {
            customer = customerDao.validateCustomer(username, password);
        } catch (CustomerNotFoundException e) {
            e.getMessage();
        }
        return customer;
    }
}
