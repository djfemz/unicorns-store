package store.data.repositories;

import store.data.models.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);
    Customer findById(int id);
    List<Customer> findAll();
    void delete(Customer customer);
    void deleteAll();
}
