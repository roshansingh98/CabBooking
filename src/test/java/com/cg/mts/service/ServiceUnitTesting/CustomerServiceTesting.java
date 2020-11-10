package com.cg.mts.service.ServiceUnitTesting;

import com.cg.mts.entities.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;
import com.cg.mts.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTesting {
    @InjectMocks
    private CustomerService service;

    @Mock
    private ICustomerRepository repo;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testInsertCustomer() throws CustomerNotFoundException {

        Customer cust = Mockito.mock(Customer.class);
        Customer updated = Mockito.mock(Customer.class);
        Mockito.when(repo.save(cust)).thenReturn(updated);
        Customer result = service.insertCustomer(cust);
        assertEquals(updated, result);

    }

    @Test
    void testUpdateCustomer_1() {
        Customer cust = Mockito.mock(Customer.class);
        Customer updated = Mockito.mock(Customer.class);
        int custId = 50;
        Mockito.when(cust.getCustomerId()).thenReturn(custId);
        Mockito.when(repo.existsById(50)).thenReturn(true);

        Mockito.when(repo.save(cust)).thenReturn(updated);
        Customer result = service.updateCustomer(cust);
        assertEquals(updated, result);

    }

    @Test
    void testUpdateCustomer_2() {
        Customer cust = Mockito.mock(Customer.class);
        int customerId = 20;
        Mockito.when(cust.getCustomerId()).thenReturn(customerId);
        Mockito.when(repo.existsById(customerId)).thenReturn(false);
        Executable exe = () -> service.updateCustomer(cust);
        assertThrows(CustomerNotFoundException.class, exe);
    }

    @Test
    void testViewCustomer() {
        Customer cust = Mockito.mock(Customer.class);
        int customerId = 31;
        Mockito.when(repo.findById(customerId)).thenReturn(Optional.of(cust));
        assertEquals(cust, service.viewCustomer(customerId));
    }

    @Test
    void testViewCustomers() {
        List<Customer> customer = new ArrayList<Customer>();
        customer.add(new Customer("ABC", "ABC", "ABC", "ABC"));
        Mockito.when(repo.findAll()).thenReturn(customer);
        assertEquals(customer , service.viewCustomers());
    }

    @Test
    void testValidateCustomers() {
        Customer customer = new Customer("ABC", "ABC", "ABC", "ABC");
        Mockito.when(repo.findByUsernameAndPassword("ABC", "ABC")).thenReturn(customer);
        assertEquals(customer, service.validateCustomer("ABC", "ABC"));
    }
}
