package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockRepository extends CrudRepository<Stock, Long> {

    Optional<Stock> findByProductName(String productName);
}
