package nl.novi.first_demo.controller;

import nl.novi.first_demo.model.Order;
import nl.novi.first_demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/orders")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Order> getCustomers() {
        return orderService.getOrders();
    }


    @GetMapping(value = "/orders/{id}")
    public Order getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    @PostMapping(value = "/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public String addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        return "Added";
    }


    @DeleteMapping(value = "/orders/{id}")
    public String deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return "Removed";
    }

    @PutMapping(value = "/orders/{id}")
    public String updateOrder(@PathVariable int id, @RequestBody Order order) {
        orderService.updateOrder(id, order);
        return "Updated";
    }



}
