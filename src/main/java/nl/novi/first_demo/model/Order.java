package nl.novi.first_demo.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")

public class Order {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String customerName;
    long customerId;
    String date;
    String paymentStatus;
    String deliveringStatus;

    //setter en getters


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getDeliveringStatus() {
        return deliveringStatus;
    }

    public void setDeliveringStatus(String deliveringStatus) {
        this.deliveringStatus = deliveringStatus;
    }






}
