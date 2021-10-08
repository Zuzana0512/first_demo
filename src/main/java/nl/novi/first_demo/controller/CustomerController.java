package nl.novi.first_demo.controller;

import nl.novi.first_demo.model.Customer;
import nl.novi.first_demo.model.Supplier;
import nl.novi.first_demo.service.CustomerService;
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
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        Long newId = customerService.addCustomer(customer);

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
}
