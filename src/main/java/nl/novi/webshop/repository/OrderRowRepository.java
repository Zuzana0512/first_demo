package nl.novi.webshop.repository;

import nl.novi.webshop.model.OrderRow;
import org.springframework.data.repository.CrudRepository;

public interface OrderRowRepository extends CrudRepository<OrderRow, Long> {
}
