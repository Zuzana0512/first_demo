package nl.novi.first_demo.service;

import nl.novi.first_demo.exeption.RecordNotFoundException;
import nl.novi.first_demo.model.Payment;
import nl.novi.first_demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Iterable<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPayment(long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()){
            return payment.get();
        }
        else {
            throw new RecordNotFoundException("Payment with id " + id + " not found.");
        }
    }

    public void addPayment(Payment payment) {
       paymentRepository.save(payment);
    }

    public void deletePayment(long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("Payment with id " + id + " not found.");
        }
    }

    public void updatePayment(long id, Payment payment) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()){
            Payment paymentInDb = optionalPayment.get();
            paymentInDb.setCustomerName(payment.getCustomerName());
            paymentInDb.setCustomerId(payment.getCustomerId());
            paymentInDb.setDate(payment.getDate());
            paymentInDb.setPaymentStatus(payment.getPaymentStatus());
        }
        else {
            throw new RecordNotFoundException("Payment with id " + id + " not found.");
        }


    }
}
