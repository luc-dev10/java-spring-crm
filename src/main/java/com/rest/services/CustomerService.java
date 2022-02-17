package com.rest.services;

import com.rest.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomers();

    void addCustomer(Customer customer);

    public Customer getCustomer(int id);

    public void deleteCustomer(int id);

    public void modifyCustomer(int id);
}
