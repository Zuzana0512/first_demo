package nl.novi.webshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String userName;

    @NotBlank
    private String password;

    private boolean enabled = true;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)

    private List<Authority> authorities = new ArrayList<>();


    public void addAuthority(Authority authority){
        this.authorities.add(authority);
    }

}
