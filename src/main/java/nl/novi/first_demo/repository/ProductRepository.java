package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{
}
