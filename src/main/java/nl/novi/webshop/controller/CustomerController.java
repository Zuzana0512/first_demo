package nl.novi.webshop.controller;

import nl.novi.webshop.dto.CustomerRequestDto;
import nl.novi.webshop.model.Customer;
import nl.novi.webshop.model.Order;
import nl.novi.webshop.service.CustomerService;
import nl.novi.webshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/customers")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Customer> getCustomers() {
        return customerService.getCustomers();
    }


    @GetMapping(value = "/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable long id) {
        return customerService.getCustomer(id);
    }


    @PostMapping(value = "/customers")
    public ResponseEntity <Object> addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        Long newId = customerService.addCustomer(customerRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body("Added " + newId);
    }


    @DeleteMapping(value = "/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return "Removed";
    }

    @PutMapping(value = "/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
        return "Updated customer";
    }

    @GetMapping(value = "/customers/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Order> getCustomerOrders(@PathVariable long id) {
        return orderService.getOrdersByCustomerId(id);
    }

}
