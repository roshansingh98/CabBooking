package com.cg.mts.dao;

import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

//        boolean success = checkExists(customer.getMobileNumber());

        Customer currentCustomer = entityManager.find(Customer.class, customer.getCustomerId());

        if (currentCustomer == null) {
            throw new CustomerNotFoundException("Cant update! Customer with the respective mobile number not found");
        }

        customer = entityManager.merge(customer);
        return customer;
    }

    @Override
    public Customer deleteCustomer(Customer customer) throws CustomerNotFoundException {
//        boolean success = checkExists(customer.getMobileNumber());

        customer = entityManager.find(Customer.class, customer.getCustomerId());

        if (customer == null) {
            throw new CustomerNotFoundException("Cant delete! Customer with the respective Customer Id not found");
        }

        entityManager.remove(customer);
        return customer;
    }

    @Override
    public List<Customer> viewCustomers() throws CustomerNotFoundException {

        List<Customer> customers = entityManager.createQuery("Select a from Customer a", Customer.class)
                .getResultList();

        if (customers.size() == 0) {
            throw new CustomerNotFoundException("No customer in the database");
        }
        return customers;
    }

    @Override
    public Customer viewCustomer(int customerId) throws CustomerNotFoundException {

        Customer customer = entityManager.find(Customer.class, customerId);

        return customer;
    }

    @Override
    public Customer validateCustomer(String username, String password) throws CustomerNotFoundException {

        Customer customer = null;
        try {
            customer = (Customer) entityManager.createNamedQuery("find customer by username and password").setParameter("Username", username).setParameter("Password", password).getSingleResult();
        } catch (NoResultException nre) {
            throw new CustomerNotFoundException("No customer found with these entries");
        }
        return customer;
    }


    /*private boolean checkExists(String mobileNumber) {
        Customer customer = entityManager.find(Customer.class, mobileNumber);
        boolean result = customer != null;
        return result;
    }*/
}
