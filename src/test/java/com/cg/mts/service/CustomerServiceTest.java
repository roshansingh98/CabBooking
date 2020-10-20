package com.cg.mts.service;

import com.cg.mts.dao.Util;
import com.cg.mts.entities.Customer;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Test
    public void testForSuccessfulInsertion() {
        Customer customer = new Customer("Customer", "123", "123456", "abc");
        Customer customer1 = new Customer("Customer1", "123", "123456", "abc");
        Customer customer2 = new Customer("Customer2", "123", "123456", "abc");

        ICustomerService customerService = new CustomerService();
        Customer c = customerService.insertCustomer(customer);
        Customer c1 = customerService.insertCustomer(customer1);

        assertEquals("Customer", customerService.viewCustomer(1).getUsername());
    }

    @AfterAll
    static void closeUp() {
        Util util = Util.getInstance();
        util.close();
    }
}