package nl.novi.webshop.repository;

import nl.novi.webshop.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
