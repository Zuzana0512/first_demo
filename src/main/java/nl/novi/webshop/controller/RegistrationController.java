package nl.novi.webshop.controller;

import lombok.AllArgsConstructor;
import nl.novi.webshop.dto.RegistrationRequestDto;
import nl.novi.webshop.service.RegistrationServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationServiceImpl registrationService;

    @PostMapping
    @RequestMapping(path = "/registration")
    public String register (@RequestBody RegistrationRequestDto request){
        return registrationService.register(request);
    }




}
