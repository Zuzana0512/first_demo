package nl.novi.webshop.repository;

import nl.novi.webshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, String > {
}
