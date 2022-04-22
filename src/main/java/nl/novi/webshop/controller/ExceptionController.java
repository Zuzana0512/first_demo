package nl.novi.webshop.controller;

import nl.novi.webshop.exeption.BadRequestException;
import nl.novi.webshop.exeption.CustomerAlreadyExistException;
import nl.novi.webshop.exeption.NotEnoughStockException;
import nl.novi.webshop.exeption.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice

public class ExceptionController {
    @ExceptionHandler(value = RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exception(RecordNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = CustomerAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exception(CustomerAlreadyExistException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = NotEnoughStockException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exception(NotEnoughStockException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exception(BadRequestException exception) {
        return exception.getMessage();
    }


}
