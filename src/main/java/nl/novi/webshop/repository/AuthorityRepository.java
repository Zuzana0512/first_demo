package nl.novi.webshop.repository;

import nl.novi.webshop.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
