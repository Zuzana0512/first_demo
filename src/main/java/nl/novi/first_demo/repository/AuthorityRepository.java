package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, String> {
}
