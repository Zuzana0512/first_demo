package nl.novi.webshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static nl.novi.webshop.util.Rounding.roundTo;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonInclude
    Customer customer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd")
    Date date;

    boolean paid = false;
    boolean delivered = false;

    @OneToOne
    Payment payment;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    List<OrderRow> orderRows = new ArrayList<>();

    public void addOrderRow(OrderRow orderRow) {
        this.orderRows.add(orderRow);
    }

    @JsonGetter("TotalExclVat")
    public double calculateTotalExclVat() {
        double total = 0;
        for (OrderRow orderRow: orderRows) {
            total += orderRow.calculateSubTotalExclVat();
        }
        return roundTo(total, 2);
    }

    @JsonGetter("TotalVat")
    public double calculateTotalVat() {
        double total = 0;
        for (OrderRow orderRow: orderRows) {
            total += orderRow.calculateSubTotalVat();
        }
        return roundTo(total, 2);
    }

    @JsonGetter("TotalInclVat")
    public double calculateTotalInclVat() {
        double total = 0;
        for (OrderRow orderRow: orderRows) {
            total += orderRow.calculateSubTotalInclVat();
        }
        return roundTo(total, 2);
    }

}
