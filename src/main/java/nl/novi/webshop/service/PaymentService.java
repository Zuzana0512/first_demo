package nl.novi.webshop.service;

import nl.novi.webshop.exeption.RecordNotFoundException;
import nl.novi.webshop.model.Payment;
import nl.novi.webshop.repository.PaymentRepository;
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

    public Long addPayment(Payment payment) {
        Payment newPayment = paymentRepository.save(payment);
        return newPayment.getId();
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
