package nl.novi.webshop.service;

import nl.novi.webshop.dto.RegistrationRequestDto;

public interface RegistrationService {
    public abstract String register(RegistrationRequestDto request);
}
