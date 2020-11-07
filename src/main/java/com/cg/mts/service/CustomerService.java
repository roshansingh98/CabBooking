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


@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Customer insertCustomer(Customer customer) {
        Customer eCustomer = customerRepository.findByUsername(customer.getUsername());
        if(eCustomer != null) {
            throw new InvalidCustomerException("Username already exists, please choose a new username");
        }
        customer = customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        boolean exists= customerRepository.existsById(customer.getCustomerId());
        if(!exists){
            throw new InvalidCustomerException("Customer does not exists for id="+customer.getCustomerId());
        }
        customer=customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Optional<Customer> optional=customerRepository.findById(customer.getCustomerId());
        if(!optional.isPresent()){
            throw new CustomerNotFoundException("Customer not found for id="+customer.getCustomerId());
        }
        customerRepository.deleteById(customer.getCustomerId());

        return customer;
    }

    @Override
    public List<Customer> viewCustomers() {
        List<Customer> listOfCustomer = customerRepository.findAll();
        if(listOfCustomer.size()==0)
        {
            throw new CustomerNotFoundException("Customer not found");
        }
        return listOfCustomer;
    }

    @Override
    public Customer viewCustomer(int customerId) {
        Optional<Customer> customers = customerRepository.findById(customerId);
        if(!customers.isPresent()) {
            throw new CustomerNotFoundException("No Customer for customer id " + customerId);
        }
        Customer customer = customers.get();
        return customer;
    }

    @Override
    public Customer validateCustomer(String username, String password) {
        Customer customer = customerRepository.findByUsernameAndPassword(username, password);
        if(customer == null) {
            throw new CustomerNotFoundException("No customer found with username " + username + " and password " + password);
        }
        return customer;
    }
}
