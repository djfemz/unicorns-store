package store.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import store.data.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);
}
