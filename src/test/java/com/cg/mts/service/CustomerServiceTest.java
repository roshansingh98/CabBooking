package com.cg.mts.service;

import com.cg.mts.util.Util;
import com.cg.mts.entities.Customer;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    //This test accomplishes to check for correct insertion and also retrieving the data by id
    @Test
    public void testForSuccessfulInsertionAndRetrieval() {
        Customer customer = new Customer("Customer", "123", "123456", "abc");

        ICustomerService customerService = new CustomerService();
        Customer c = customerService.insertCustomer(customer);

        assertEquals("Customer", customerService.viewCustomer(1).getUsername());
    }

    @AfterAll
    static void closeUp() {
        Util util = Util.getInstance();
        util.close();
    }
}