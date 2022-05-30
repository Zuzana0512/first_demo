package nl.novi.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static nl.novi.webshop.util.Rounding.roundTo;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_rows")
public class OrderRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    private int quantity;

    @ManyToOne
    @JsonBackReference
    private Order order;

    @JsonGetter("SubTotalExclVat")
    public double calculateSubTotalExclVat() {
        return roundTo(quantity * product.getPriceThreeSeeds(), 2);
    }

    @JsonGetter("SubTotalVatPercentage")
    public double calculateSubTotalVatPercentage() {
        return product.getVatPercentage();
    }

    @JsonGetter("SubTotalVat")
    public double calculateSubTotalVat() {
        return roundTo(quantity * product.getPriceThreeSeeds() * product.getVatPercentage() / 100, 2);
    }

    @JsonGetter("SubTotalInclVat")
    public double calculateSubTotalInclVat() {
        return calculateSubTotalExclVat() + calculateSubTotalVat();
    }

}
