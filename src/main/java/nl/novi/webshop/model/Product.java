package nl.novi.webshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String kind;
    private String description;
    private String photo;
    private double priceOneSeed = 0;
    private double priceThreeSeeds = 0;
    private double priceFiveSeeds = 0;
    private double priceTenSeeds = 0;
    private int vatPercentage = 0;



}
