package nl.novi.webshop.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "customers")

public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;
    private String lastname;
    private String address;


    @OneToMany
    @JsonIgnore
    List<Order> orders;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private User user;

    public Customer(String firstname, String lastname, String address, String email, String password, boolean b){
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }


    @JsonGetter("numberOfOrders")
    public int amountOfOrders(){
        return orders.size();
    }


    public void addOrder(Order order) {
        this.orders.add(order);
    }

}
