package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
}
