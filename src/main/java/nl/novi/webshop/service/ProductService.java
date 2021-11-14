package nl.novi.webshop.service;

import nl.novi.webshop.model.Product;

public interface ProductService {

    public Iterable<Product> getProducts();

    public abstract Product getProduct(long id);

    public abstract Long addProduct(Product product);

    public abstract void deleteProduct(long id);

    public abstract void updateProduct(long id, Product product);

}
