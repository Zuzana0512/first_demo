package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.OrderRow;
import org.springframework.data.repository.CrudRepository;

public interface OrderRowRepository extends CrudRepository<OrderRow, Long> {
}
