package com.rest.controller;

import com.rest.entity.Customer;
import com.rest.error.CustomerNotFoundException;
import com.rest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {

    // Customer Service
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<List<Customer>> readCustomers() {
        List<Customer> customerList = customerService.getCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<Customer> readCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null)
            throw new CustomerNotFoundException("Customer not found.");

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer customerDb = customerService.modifyCustomer(customer);
        return new ResponseEntity<>(customerDb, HttpStatus.OK);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomer(customerId);
        if (customer == null)
            throw new CustomerNotFoundException("Cannot delete user. Customer not found");
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(String.format("user deleted: %d - %s", customerId, customer.getEmail()), HttpStatus.OK);
    }

}
