package com.cg.mts.service;

import com.cg.mts.dao.CustomerDao;
import com.cg.mts.exception.InvalidCustomerException;
import com.cg.mts.util.PersistanceUtil;
import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerService implements ICustomerService {

    private ICustomerRepository customerDao;

    private EntityManager entityManager;

    public CustomerService() {
        PersistanceUtil persistanceUtil = PersistanceUtil.getInstance();
        entityManager = persistanceUtil.getEntityManager();
        customerDao = new CustomerDao(entityManager);
    }

    @Override
    public Customer insertCustomer(Customer customer) {

        try {
            if (customer.getEmail() == null) {
                throw new InvalidCustomerException("Null values not accepted");
            } else if (!Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", customer.getEmail())) {
                throw new InvalidCustomerException("email should be of type abc@gmail.com");
            } else if (!Pattern.matches("(0/91)?[7-9][0-9]{9}", customer.getMobileNumber())) {
                throw new InvalidCustomerException("phone number is not valid. it should 9600XXXXX");
            } else if (customer.getPassword().length() < 6) {
                throw new InvalidCustomerException("password should be 6 characters or more");
            } else if (customer.getUsername().length() < 6) {
                throw new InvalidCustomerException("username should be six characters or more");
            } else if (customer.getMobileNumber() == null) {
                throw new InvalidCustomerException("Mobile Number cannot be null");
            } else if (customer.getPassword() == null) {
                throw new InvalidCustomerException("Password cannot be null");
            } else if (customer.getUsername() == null) {
                throw new InvalidCustomerException("Username cannot be null");
            }
        } catch (InvalidCustomerException e) {
            System.out.println(e.getMessage());
            return new Customer();
        }

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        customer = customerDao.insertCustomer(customer);
        entityTransaction.commit();
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {

        try {
            if (customer.getEmail() == null) {
                throw new InvalidCustomerException("Null values not accepted");
            } else if (!Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", customer.getEmail())) {
                throw new InvalidCustomerException("email should be of type abc@gmail.com");
            } else if (!Pattern.matches("(0/91)?[7-9][0-9]{9}", customer.getMobileNumber())) {
                throw new InvalidCustomerException("phone number is not valid. it should 9600XXXXX");
            } else if (customer.getPassword().length() < 6) {
                throw new InvalidCustomerException("password should be 6 characters or more");
            } else if (customer.getUsername().length() < 6) {
                throw new InvalidCustomerException("username should be six characters or more");
            } else if (customer.getMobileNumber() == null) {
                throw new InvalidCustomerException("Mobile Number cannot be null");
            } else if (customer.getPassword() == null) {
                throw new InvalidCustomerException("Password cannot be null");
            } else if (customer.getUsername() == null) {
                throw new InvalidCustomerException("Username cannot be null");
            }
        } catch (InvalidCustomerException e) {
            System.out.println(e.getMessage());
            return new Customer();
        }

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
