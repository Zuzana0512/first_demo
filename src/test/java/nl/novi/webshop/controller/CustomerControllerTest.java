package nl.novi.webshop.controller;

/*import nl.novi.testjunitjupiter.TestJunitJupiterApplication;
import nl.novi.testjunitjupiter.model.Customer;
import nl.novi.testjunitjupiter.service.CustomerService;

import nl.novi.webshop.WebshopApplication;
import nl.novi.webshop.model.Customer;
import nl.novi.webshop.service.CustomerService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@ContextConfiguration(classes={WebshopApplication.class})
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testEndpointCustomers() throws Exception {

        Customer customer = new Customer("Zuzana", "Sorokova", "Amsterdam", "zuzana@gmail.com", "password", true);
        List<Customer> customers = Arrays.asList(customer);

        given(customerService.getCustomers()).willReturn(customers);

        mvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].lastName", is(customer.getLastname())));
    }

}

 */