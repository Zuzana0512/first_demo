package nl.novi.first_demo.model;

import javax.persistence.*;

@Entity
@Table(name = "payments")

public class Payment {
    //attributes
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    long customerId;
    String customerName;
    long orderId;
    String date;
    float totalAmount;
    String paymentStatus;

    //setter en getters


    public long getId() {
        return id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setId(long id) {
        this.id = id;
    }
}
