package nl.novi.first_demo.service;


import nl.novi.first_demo.dto.CustomerRequestDto;
import nl.novi.first_demo.exeption.RecordNotFoundException;
import nl.novi.first_demo.model.Customer;
import nl.novi.first_demo.model.User;
import nl.novi.first_demo.repository.CustomerRepository;
import nl.novi.first_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static List<Customer> customers = new ArrayList<>();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            return customer.get();
        }
        else {
            throw new RecordNotFoundException("Customer with id " + id + " not found.");
        }
    }

    public Long addCustomer(CustomerRequestDto customerRequestDto) {
        Customer newCustomer = new Customer();
        newCustomer.setName(customerRequestDto.getName());
        newCustomer.setAddress(customerRequestDto.getAddress());
        newCustomer.setEmail(customerRequestDto.getEmail());
        Customer savedCustomer = customerRepository.save(newCustomer);
        User newUser = new User();
        newUser.setUserName(savedCustomer.getEmail());
        String password = customerRequestDto.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encryptedPassword);
        newUser.setEnabled(true);
        newUser.setCustomer(savedCustomer);
        User savedUser = userRepository.save(newUser);
        savedCustomer.setUser(savedUser);
        customerRepository.save(savedCustomer);
        userService.addAuthority(newUser.getUserName(), "ROLE_CUSTOMER");
        return newCustomer.getId();
    }


    public void deleteCustomer(long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("Customer with id " + id + " not found.");
        }
    }

    public void updateCustomer(long id, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()){
            Customer customerInDb = optionalCustomer.get();
            customerInDb.setName(customer.getName());
            customerInDb.setAddress(customer.getAddress());
            customerInDb.setEmail(customer.getEmail());
            customerRepository.save(customerInDb);
        }
        else {
            throw new RecordNotFoundException("Customer with id " + id + " not found.");
        }


    }

}