package nl.novi.webshop.service;

import nl.novi.webshop.dto.CustomerRequestDto;
import nl.novi.webshop.model.Customer;


public interface CustomerService {

    Iterable<Customer> getCustomers();


    Customer getCustomer(long id);

    Long addCustomer(CustomerRequestDto customerRequestDto);

   void deleteCustomer(long id);

   void updateCustomer(long id, Customer customer);

}
