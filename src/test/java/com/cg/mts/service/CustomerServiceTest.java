package com.cg.mts.service;

import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.util.Util;
import com.cg.mts.entities.Customer;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {


    //This test accomplishes to check for correct insertion and also retrieving the data by id
    @Test
    public void testForSuccessfulInsertionAndRetrieval() {
        Customer customer = new Customer("Customer", "123456", "9600456781", "abc@gmail.com");

        ICustomerService customerService = new CustomerService();
        Customer c = customerService.insertCustomer(customer);

        assertEquals("Customer", customerService.viewCustomer(1).getUsername());
    }

    @Test
    public void testForInsertCustomer() {

        Customer customer = new Customer("Username", "Password", "9600456781", "xyz@gmail.com");

        ICustomerService customerService = new CustomerService();

        assertEquals(customer, customerService.insertCustomer(customer));

    }


    @Test
    public void testForCorrectUpdating() {

        Customer customer = new Customer("Username", "Password", "9600456781", "xyz@gmail.com");
        Customer customer1 = new Customer("UserShort", "PassShort", "9600456781", "xyz@gmail.com");
        Customer customer2 = new Customer("newUsername", "PassWo", "9600456781", "xyz@gmail.com");

        ICustomerService customerService = new CustomerService();

        customerService.insertCustomer(customer);
        customerService.insertCustomer(customer1);
        customerService.insertCustomer(customer2);

        Customer newCustomer = new Customer("updatedCustomer", "newPass", "9600456781", "adsdaf@fsdf.com");
        newCustomer.setCustomerId(customer1.getCustomerId());

        assertEquals("updatedCustomer", customerService.updateCustomer(newCustomer).getUsername());

    }

    @Test
    public void testForCorrectDeletion() {

        Customer customer = new Customer("Username", "Password", "9600456781", "xyz@gmail.com");
        Customer customer1 = new Customer("UserShort", "PassShort", "9600456781", "xyz@gmail.com");
        Customer customer2 = new Customer("newUsername", "PassWo", "9600456781", "xyz@gmail.com");

        ICustomerService customerService = new CustomerService();

        customerService.insertCustomer(customer);
        customerService.insertCustomer(customer1);
        customerService.insertCustomer(customer2);

        Customer customer3 = customerService.deleteCustomer(customer1);

        assertEquals(null, customerService.viewCustomer(customer1.getCustomerId()));

//        assertEquals(0, customerService.deleteCustomer(customer1).getCustomerId());
//        Exception exception = assertThrows(CustomerNotFoundException.class , () -> {customerService.deleteCustomer(customer1);});
//        assertEquals("Cant delete! Customer with the respective Customer Id not found" ,customerService.deleteCustomer(customer1) );

    }

    @Test
    public void testForViewCustomers() {

        Customer customer = new Customer("Username", "Password", "9600456781", "xyz@gmail.com");
        Customer customer1 = new Customer("UserShort", "PassShort", "9600456781", "xyz@gmail.com");
        Customer customer2 = new Customer("newUsername", "PassWo", "9600456781", "xyz@gmail.com");

        ICustomerService customerService = new CustomerService();

        customerService.insertCustomer(customer);
        customerService.insertCustomer(customer1);
        customerService.insertCustomer(customer2);

        List<Integer> string = new ArrayList<>();

        for (Customer cust : customerService.viewCustomers()) {
            string.add(cust.getCustomerId());
        }

        assertEquals(2, string.get(1));


    }

    @Test
    public void testToValidateCustomer() {

        Customer customer = new Customer("Username", "Password", "84569745", "xyz@gmail.com");
        Customer customer1 = new Customer("User", "Pass", "84569745", "xyz@gmail.com");
        Customer customer2 = new Customer("newUsername", "Passw", "84569745", "xyz@gmail.com");

        ICustomerService customerService = new CustomerService();

        customerService.insertCustomer(customer);
        customerService.insertCustomer(customer1);
        customerService.insertCustomer(customer2);

        Customer customerValidation = customerService.validateCustomer("Username", "Password");

        assertEquals(1, customerValidation.getCustomerId());

    }


    @AfterAll
    static void closeUp() {
        Util util = Util.getInstance();
        util.close();
    }
}