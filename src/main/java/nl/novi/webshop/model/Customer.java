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

public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;
    private String lastname;
    private String address;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Authority authority;
    private Boolean enabled;

    @Column(unique = true)
    @Email(message = "Please enter a valid e-mail address")
    private String email;

    private String password;

    @OneToMany
    @JsonIgnore
    List<Order> orders;

    @OneToOne
    @JsonIgnore
    private User user;

    public Customer(String firstname, String lastname, String address, Authority authority, Boolean enabled, String email, String password, User user) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.authority = authority;
        this.enabled = enabled;
        this.email = email;
        this.password = password;
        this.user = user;
    }

    public Customer(String firstname, String lastname, String address, String email, String password, boolean enabled){
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.authority = new Authority();
        this.authority.setAuthority("ROLE_USER");
        this.authority.setUsername("customer");
    }

    @JsonGetter("numberOfOrders")
    public int amountOfOrders(){
        return orders.size();
    }

   @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(getAuthority().getAuthority());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

}
