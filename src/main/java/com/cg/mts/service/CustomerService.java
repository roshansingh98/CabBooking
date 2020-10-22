package com.cg.mts.service;

import com.cg.mts.dao.CustomerDao;
import com.cg.mts.util.Util;
import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {

    private ICustomerRepository customerDao;

    private EntityManager entityManager;

    public CustomerService() {
        Util util = Util.getInstance();
        entityManager = util.getEntityManager();
        customerDao = new CustomerDao(entityManager);
    }

    @Override
    public Customer insertCustomer(Customer customer) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        customer = customerDao.insertCustomer(customer);
        entityTransaction.commit();
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            customer = customerDao.updateCustomer(customer);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            entityTransaction.commit();
            return new Customer();
        }

        entityTransaction.commit();

        return customer;
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            customer = customerDao.deleteCustomer(customer);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            entityTransaction.commit();
            return new Customer();
        }

        entityTransaction.commit();

        return customer;
    }

    @Override
    public List<Customer> viewCustomers() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Customer> customers = null;
        try {
            customers = customerDao.viewCustomers();
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            entityTransaction.commit();
            return new ArrayList<Customer>();
        }
        entityTransaction.commit();
        return customers;
    }

    @Override
    public Customer viewCustomer(int customerId) {
        Customer customer = null;
        try {
            customer = customerDao.viewCustomer(customerId);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            return new Customer();
        }
        return customer;
    }

    @Override
    public Customer validateCustomer(String username, String password) {
        Customer customer = null;
        try {
            customer = customerDao.validateCustomer(username, password);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
            return new Customer();
        }
        return customer;
    }
}
