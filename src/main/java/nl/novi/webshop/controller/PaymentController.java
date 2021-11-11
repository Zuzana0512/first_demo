package nl.novi.webshop.controller;

import nl.novi.webshop.model.Payment;
import nl.novi.webshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/payments")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Payment> getPayments() {
        return paymentService.getPayments();
    }


    @GetMapping(value = "/payments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Payment getPayment(@PathVariable long id) {
        return paymentService.getPayment(id);
    }

    @PostMapping(value = "/payments")
    public ResponseEntity addPayment(@RequestBody Payment payment){
        Long newId = paymentService.addPayment(payment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body("Added " + newId);
    }


    @DeleteMapping(value = "/payments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletePayment(@PathVariable long id) {
        paymentService.deletePayment(id);
        return "Removed";
    }

    @PutMapping(value = "/payments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updatePayment(@PathVariable long id, @RequestBody Payment payment) {
        paymentService.updatePayment(id, payment);
        return "Updated customer";
    }
}
