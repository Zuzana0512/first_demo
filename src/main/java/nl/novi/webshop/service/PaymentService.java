package nl.novi.webshop.service;

import nl.novi.webshop.model.Payment;

public interface PaymentService {
    public abstract Iterable<Payment> getPayments();

    public abstract Payment getPayment(long id);

    public abstract Long addPayment(Payment payment);

    public abstract void deletePayment(long id);

    public abstract void updatePayment(long id, Payment payment);


    }
