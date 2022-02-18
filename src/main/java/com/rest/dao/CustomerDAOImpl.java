package com.rest.dao;

import com.rest.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // inject session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
        List<Customer> customerList = query.getResultList();
        return customerList;
    }

    @Override
    @Transactional
    public void addCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(customer);

    }

    @Override
    @Transactional
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);

        return customer;
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = getCustomer(id);
        if (customer != null)
            session.delete(customer);
    }

    @Override
    @Transactional
    public Customer modifyCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customerDb = getCustomer(id);
        if (customerDb != null)
            session.update(customerDb);

        return customerDb;
    }
}
