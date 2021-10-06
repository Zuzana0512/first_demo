package nl.novi.first_demo.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Attributes
    String date;
    Boolean paymentStatus;
    Boolean deliveringStatus;

    //setter en getters


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

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Boolean getDeliveringStatus() {
        return deliveringStatus;
    }

    public void setDeliveringStatus(Boolean deliveringStatus) {
        this.deliveringStatus = deliveringStatus;
    }
}
