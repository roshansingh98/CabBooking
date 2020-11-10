package com.cg.mts.service;

import com.cg.mts.exception.InvalidCustomerException;
import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/* This annotation marks the class as a service class */
@Service
/*
 * @Transactional marks the transaction that we have to do in the methods while
 * updating the data
 */
@Transactional
public class CustomerService implements ICustomerService {

    /*
     * ICustomerRepository is a interface which extends JPA repository. We are
     * trying to call the reference of it. Using @Autowired annotation , the object
     * of the repository will be created during run time dynamicaly
     */
    @Autowired
    private ICustomerRepository customerRepository;

    /*
     * this method will call customerRepository and will add the customer object
     * using save method of the JPA repository. if the customer enters the username
     * which already exixsts, it will throw invalid customer exception
     */
    @Override
    public Customer insertCustomer(Customer customer) {
        Customer eCustomer = customerRepository.findByUsername(customer.getUsername());
        if (eCustomer != null) {
            throw new InvalidCustomerException("Username already exists, please choose a new username");
        }
        customer = customerRepository.save(customer);
        return customer;
    }

    /*
     * this method will call customerRepository and will update the customer object
     * for matching customerId, if there is no matching customer id, then we will
     * return customerNotFound exception
     */
    @Override
    public Customer updateCustomer(Customer customer) {
        boolean exists = customerRepository.existsById(customer.getCustomerId());
        if (!exists) {
            throw new CustomerNotFoundException("Customer does not exists for id=" + customer.getCustomerId());
        }
        customer = customerRepository.save(customer);
        return customer;
    }

    /*
     * this method will delete the customer object for matching cutomer id. if there
     * is no matching customer id, then it will throw an exception customer not
     * found
     */
    @Override
    public Customer deleteCustomer(Customer customer) {
        Optional<Customer> optional = customerRepository.findById(customer.getCustomerId());
        if (!optional.isPresent()) {
            throw new CustomerNotFoundException("Customer not found for id=" + customer.getCustomerId());
        }
        customerRepository.deleteById(customer.getCustomerId());

        return customer;
    }

    /*
     * this method will return list of all the customers from the database. if there
     * are no customer in the database, it will throw exception: customer not found
     * exception
     */
    @Override
    public List<Customer> viewCustomers() {
        List<Customer> listOfCustomer = customerRepository.findAll();
        if (listOfCustomer.size() == 0) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return listOfCustomer;
    }

    /*
     * this method will return customer object with matching customer id. if there
     * is no matching id, then it wil throw customer not found exception
     */
    @Override
    public Customer viewCustomer(int customerId) {
        Optional<Customer> customers = customerRepository.findById(customerId);
        if (!customers.isPresent()) {
            throw new CustomerNotFoundException("No Customer for customer id " + customerId);
        }
        Customer customer = customers.get();
        return customer;
    }

    /*
     * this method will return customer object with matching username and password.
     * if there is no matching username and password, we will throw customer not
     * found exception
     */
    @Override
    public Customer validateCustomer(String username, String password) {
        Customer customer = customerRepository.findByUsernameAndPassword(username, password);
        if (customer == null) {
            throw new CustomerNotFoundException(
                    "No customer found with username " + username + " and password " + password);
        }
        return customer;
    }
}
