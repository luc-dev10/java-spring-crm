package com.rest.controller;

import com.rest.entity.Customer;
import com.rest.error.CustomerNotFoundException;
import com.rest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {

    // Customer Service
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customerList = customerService.getCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<Customer> getCustomers(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not found.");

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
