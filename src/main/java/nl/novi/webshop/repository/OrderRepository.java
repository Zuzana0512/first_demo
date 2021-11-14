package nl.novi.webshop.repository;

import nl.novi.webshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerId(long customerId);
    List<Order> findAllByPaid(boolean paid);
    List<Order> findAllByDelivered(boolean delivered);
    List<Order> findAllByPaidAndDelivered(boolean paid, boolean delivered);

}
