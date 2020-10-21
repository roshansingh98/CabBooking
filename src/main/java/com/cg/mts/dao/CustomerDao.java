package com.cg.mts.dao;

import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerDao implements ICustomerRepository {

    private EntityManager entityManager;

    public CustomerDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Customer insertCustomer(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }


    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {

        boolean success = checkExists(customer.getMobileNumber());

        if (!success) {
            throw new CustomerNotFoundException("Cant update! Customer with the respective mobile number not found");
        }

        customer = entityManager.merge(customer);
        return customer;
    }

    @Override
    public Customer deleteCustomer(Customer customer) throws CustomerNotFoundException {
        boolean success = checkExists(customer.getMobileNumber());

        if (!success) {
            throw new CustomerNotFoundException("Cant update! Customer with the respective mobile number not found");
        }

        entityManager.remove(customer);
        return customer;
    }

    @Override
    public List<Customer> viewCustomers() throws CustomerNotFoundException {

        List<Customer> customers = entityManager.createQuery("Select a from Customer a", Customer.class).getResultList();

        if (customers == null) {
            throw new CustomerNotFoundException("No customer in the database");
        }
        return customers;
    }

    @Override
    public Customer viewCustomer(int customerId) throws CustomerNotFoundException {

        Customer customer = entityManager.find(Customer.class, customerId);

        return null;
    }

    @Override
    public Customer validateCustomer(String username, String password) throws CustomerNotFoundException {
        return null;
    }


    private boolean checkExists(String mobileNumber) {
        Customer customer = entityManager.find(Customer.class, mobileNumber);
        boolean result = customer != null;
        return result;
    }
}
