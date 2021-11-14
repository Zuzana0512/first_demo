package nl.novi.webshop.service;

import nl.novi.webshop.dto.OrderRequestDto;
import nl.novi.webshop.model.Order;

import java.util.Optional;

public interface OrderService {
    public abstract Iterable<Order> getOrders();

    public abstract Iterable<Order> getOrders(Optional<Boolean> paid, Optional<Boolean> delivered);

    public abstract Order getOrder(long id);

    public abstract Iterable<Order> getOrdersByCustomerId(long customerId);

    public abstract Long addOrder(OrderRequestDto orderRequestDto);

    public abstract void deleteOrder(long id);

    public abstract void updateOrder(long id, Order order);

    public abstract void setOrderDelivered(long id, boolean isDelivered);

    public abstract void setOrderPaid(long id, boolean isPaid);
}
