package nl.novi.webshop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CustomerRequestDto {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String address;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;


}
