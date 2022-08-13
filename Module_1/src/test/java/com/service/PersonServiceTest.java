package com.service;

import com.model.customer.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.service.PersonService.createNewCustomer;

class PersonServiceTest {
   private PersonService target;

    @BeforeEach
    void setUp() {
    }


    @Test
    void testCreateNewCustomer() {
        Customer customer1 = createNewCustomer();
        Assertions.assertNotNull(customer1);
    }

    @Test
    void testCreateNewCustomerCheckHasCorrectDomain() {
        Customer customer = new Customer("123@mail.ru", 12);
        Customer customer1 = createNewCustomer();
        Assertions.assertNotEquals(customer.getEmail(), String.valueOf(customer1.getEmail()));
    }
}