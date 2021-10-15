package nl.novi.first_demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")

public class Order {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String customerName;
    long customerId;
    Date date;
    boolean paid = false;
    boolean delivered = false;


    @OneToMany(mappedBy = "order")
    List<OrderRegel> orderRegels = new ArrayList<>();

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


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public List<OrderRegel> getOrderRegels() {
        return orderRegels;
    }

    public void setOrderRegels(List<OrderRegel> orderRegels) {
        this.orderRegels = orderRegels;
    }
}
