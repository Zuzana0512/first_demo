package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String > {
}
