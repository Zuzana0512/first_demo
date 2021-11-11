package nl.novi.webshop.repository;

import nl.novi.webshop.model.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockRepository extends CrudRepository<Stock, Long> {

    Optional<Stock> findByProductName(String productName);
}
