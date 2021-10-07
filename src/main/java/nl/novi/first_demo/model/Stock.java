package nl.novi.first_demo.model;

import javax.persistence.*;

@Entity
@Table(name = "stock")

public class Stock {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String productName;
    int productAmount;
    String productNameSupplier;
    String supplierName;

    //setters en getters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductNameSupplier() {
        return productNameSupplier;
    }

    public void setProductNameSupplier(String productNameSupplier) {
        this.productNameSupplier = productNameSupplier;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
