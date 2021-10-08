package nl.novi.first_demo.service;

import nl.novi.first_demo.exeption.RecordNotFoundException;
import nl.novi.first_demo.model.Order;
import nl.novi.first_demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new RecordNotFoundException("Order with id " + id + " not found.");
        }
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrder(long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Order with id " + id + " not found.");
        }
    }

    public void updateOrder(long id, Order order) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order orderInDb = optionalOrder.get();
            orderInDb.setCustomerName(order.getCustomerName());
            orderInDb.setCustomerId(order.getCustomerId());
            orderInDb.setDate(order.getDate());
            orderInDb.setDeliveringStatus(order.getDeliveringStatus());
            orderInDb.setPaymentStatus(order.getPaymentStatus());
            orderRepository.save(orderInDb);
        } else {
            throw new RecordNotFoundException("Order with id " + id + " not found.");
        }


    }
}