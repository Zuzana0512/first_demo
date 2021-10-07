package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
