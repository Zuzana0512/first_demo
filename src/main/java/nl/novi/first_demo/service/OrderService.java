package nl.novi.first_demo.service;

import nl.novi.first_demo.dto.OrderRowDto;
import nl.novi.first_demo.dto.OrderRequestDto;
import nl.novi.first_demo.exeption.RecordNotFoundException;
import nl.novi.first_demo.model.Customer;
import nl.novi.first_demo.model.Order;
import nl.novi.first_demo.model.OrderRow;
import nl.novi.first_demo.model.Product;
import nl.novi.first_demo.repository.CustomerRepository;
import nl.novi.first_demo.repository.OrderRepository;
import nl.novi.first_demo.repository.OrderRowRepository;
import nl.novi.first_demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderRowRepository orderRowRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StockService stockService;

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Iterable<Order> getOrders(Optional<Boolean> paid, Optional<Boolean> delivered) {
        if (paid.isPresent())
            if (delivered.isPresent())
                return orderRepository.findAllByPaidAndDelivered(paid.get(), delivered.get());
            else
                return orderRepository.findAllByPaid(paid.get());
        else
        if (delivered.isPresent())
            return orderRepository.findAllByDelivered(delivered.get());
        else
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

    public Iterable<Order> getOrdersByCustomerId(long customerId) {
        if (customerRepository.existsById(customerId)) {
            return orderRepository.findAllByCustomerId(customerId);
        }
        else {
            throw new RecordNotFoundException("Customer with id " + customerId + " not found.");
        }
    }

    public Long addOrder(OrderRequestDto orderRequestDto) {

        Order order = new Order();
        order.setDate(orderRequestDto.getDate());
        Order newOrder = orderRepository.save(order);

        // add customer
        long customerId = orderRequestDto.getCustomerId();
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new RecordNotFoundException("Customer with id " + customerId + "does not exist");
        }
        Customer customer = optionalCustomer.get();

        newOrder.setCustomer(customer);
        customer.addOrder(newOrder);

        for(OrderRowDto orderRowDto: orderRequestDto.getOrderRowDtos()) {
            OrderRow orderRow = new OrderRow();
            orderRow.setQuantity(orderRowDto.getQuantity());

            long productId = orderRowDto.getProductId();
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isEmpty()) {
                throw new RecordNotFoundException("Product with id " + customerId + "does not exist");
            }

            Product product = optionalProduct.get();
            stockService.takeOfStock(product.getName(), orderRowDto.getQuantity());
            orderRow.setProduct(product);

            orderRow.setOrder(order);

            OrderRow newOrderRow = orderRowRepository.save(orderRow);

            order.addOrderRow(newOrderRow);
        }

        newOrder = orderRepository.save(order);
        return newOrder.getId();
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
            orderInDb.setDelivered(order.isDelivered());
            orderInDb.setPaid(order.isPaid());
            orderRepository.save(orderInDb);
        } else {
            throw new RecordNotFoundException("Order with id " + id + " not found.");
        }
    }

    public void setOrderDelivered(long id, boolean isDelivered) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setDelivered(isDelivered);
            orderRepository.save(order);
        } else {
            throw new RecordNotFoundException("Order with id " + id + " not found.");
        }
    }

    public void setOrderPaid(long id, boolean isPaid) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setPaid(isPaid);
            orderRepository.save(order);
        } else {
            throw new RecordNotFoundException("Order with id " + id + " not found.");
        }
    }
}