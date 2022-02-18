package com.rest.dao;

import com.rest.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers();

    public void addCustomer(Customer customer);

    public Customer getCustomer(int id);

    public void deleteCustomer(int id);

    public Customer modifyCustomer(Customer customer);
}
