package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
