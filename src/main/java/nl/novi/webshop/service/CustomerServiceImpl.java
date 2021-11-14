package nl.novi.webshop.service;


import lombok.AllArgsConstructor;
import nl.novi.webshop.dto.CustomerRequestDto;
import nl.novi.webshop.exeption.CustomerAlreadyExistException;
import nl.novi.webshop.exeption.RecordNotFoundException;
import nl.novi.webshop.model.Customer;
import nl.novi.webshop.model.User;
import nl.novi.webshop.repository.CustomerRepository;
import nl.novi.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        newCustomer.setFirstname(customerRequestDto.getFirstname());
        newCustomer.setLastname(customerRequestDto.getLastname());
        newCustomer.setEmail(customerRequestDto.getEmail());
        customerRepository.save(newCustomer);
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
            customerInDb.setFirstname(customer.getFirstname());
            customerInDb.setLastname(customer.getLastname());
            customerInDb.setAddress(customer.getAddress());
            customerInDb.setEmail(customer.getEmail());
            customerRepository.save(customerInDb);
        }
        else {
            throw new RecordNotFoundException("Customer with id " + id + " not found.");
        }


    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("USER_NOT_FOUND_MSG")));
    }

    public String signUp(Customer customer){
        boolean userExists = customerRepository
                .findByEmail(customer.getEmail())
                .isPresent();
        if(userExists){
            throw new CustomerAlreadyExistException("This email is already used.");
        }

        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        customerRepository.save(customer);
        return "You are singed.";
    }
}
