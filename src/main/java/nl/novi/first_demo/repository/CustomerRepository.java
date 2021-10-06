package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
