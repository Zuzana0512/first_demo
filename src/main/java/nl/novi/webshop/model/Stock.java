package nl.novi.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stock")

public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productName;
    private int productAmount;
    private String supplierName;


    public Stock(String productName, int productAmount, String supplierName) {
        this.productName = productName;
        this.productAmount = productAmount;
        this.supplierName = supplierName;
    }
}
