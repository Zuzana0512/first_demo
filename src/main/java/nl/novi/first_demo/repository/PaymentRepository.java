package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
