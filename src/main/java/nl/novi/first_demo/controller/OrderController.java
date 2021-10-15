package nl.novi.first_demo.controller;

import nl.novi.first_demo.dto.OrderRequestDto;
import nl.novi.first_demo.model.Order;
import nl.novi.first_demo.model.Supplier;
import nl.novi.first_demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    @ResponseStatus(HttpStatus.OK)
    public Order getOrder(@PathVariable long id) {
        return orderService.getOrder(id);
    }

    @PostMapping(value = "/orders")
    public ResponseEntity addOrder(@RequestBody OrderRequestDto orderRequestDto){
        Long newId = orderService.addOrder(orderRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body("Added " + newId);
    }


    @DeleteMapping(value = "/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
        return "Removed";
    }

    @PutMapping(value = "/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateOrder(@PathVariable long id, @RequestBody Order order) {
        orderService.updateOrder(id, order);
        return "Updated";
    }



}
