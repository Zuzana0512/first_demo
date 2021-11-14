package nl.novi.webshop.service;

import nl.novi.webshop.dto.CustomerRequestDto;
import nl.novi.webshop.model.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface CustomerService extends UserDetailsService {
    public abstract Iterable<Customer> getCustomers();


    public abstract Customer getCustomer(long id);

    public abstract Long addCustomer(CustomerRequestDto customerRequestDto);

    public abstract void deleteCustomer(long id);

    public abstract void updateCustomer(long id, Customer customer);


    @Override
    public UserDetails loadUserByUsername(String email);

    public abstract String signUp(Customer customer);
}
