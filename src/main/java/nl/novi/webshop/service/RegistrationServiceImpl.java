package nl.novi.webshop.service;


import lombok.AllArgsConstructor;
import nl.novi.webshop.dto.RegistrationRequestDto;
import nl.novi.webshop.model.Customer;
import nl.novi.webshop.util.EmailValidation;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

    private final EmailValidation emailValidation;
    private final CustomerServiceImpl customerService;

    public String register(RegistrationRequestDto request) {
        boolean isValidEmail = emailValidation.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalMonitorStateException("Email is not valid");
        }
        return customerService.signUp(new Customer(request.getFirstname(), request.getLastname(), request.getAddress(), request.getEmail(), request.getPassword(), true));
    }


}