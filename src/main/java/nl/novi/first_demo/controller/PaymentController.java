package nl.novi.first_demo.controller;

import nl.novi.first_demo.model.Payment;
import nl.novi.first_demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Payment getPayment(@PathVariable int id) {
        return paymentService.getPayment(id);
    }

    @PostMapping(value = "/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public String addPayment(@RequestBody Payment payment) {
        paymentService.addPayment(payment);
        return "Added";
    }


    @DeleteMapping(value = "/payments/{id}")
    public String deletePayment(@PathVariable int id) {
        paymentService.deletePayment(id);
        return "Removed";
    }

    @PutMapping(value = "/payments/{id}")
    public String updatePayment(@PathVariable int id, @RequestBody Payment payment) {
        paymentService.updatePayment(id, payment);
        return "Updated customer";
    }
}
