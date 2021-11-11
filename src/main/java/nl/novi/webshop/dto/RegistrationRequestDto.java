package nl.novi.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RegistrationRequestDto {

    private final String firstname;
    private final String lastname;
    private final String address;
    private final String password;

    @Email
    private final String email;

}
