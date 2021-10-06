package nl.novi.first_demo.controller;

import nl.novi.first_demo.model.Customer;
import nl.novi.first_demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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
    public Customer getCustomer(@PathVariable int id) {
        return customerService.getCustomer(id);
    }

    @PostMapping(value = "/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return "Added";
    }


    @DeleteMapping(value = "/customers/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return "Removed";
    }

    @PutMapping(value = "/customers/{id}")
    public String updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
        return "Updated customer";
    }
}
