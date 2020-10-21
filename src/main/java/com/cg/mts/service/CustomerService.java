package com.cg.mts.service;

import com.cg.mts.dao.CustomerDao;
import com.cg.mts.dao.Util;
import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CustomerService implements ICustomerService {

    private ICustomerRepository customerDao;

    private EntityManager em;
    EntityTransaction et;

    public CustomerService() {
        Util util = Util.getInstance();
        em = util.getEntityManager();
        et = em.getTransaction();
        customerDao = new CustomerDao(em);
    }

    @Override
    public Customer insertCustomer(Customer customer) {
        et.begin();
        customer = customerDao.insertCustomer(customer);
        et.commit();
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
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
        return customer;
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
