package nl.novi.webshop.service;

import nl.novi.webshop.exeption.RecordNotFoundException;
import nl.novi.webshop.model.Product;
import nl.novi.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            return product.get();
        }
        else {
            throw new RecordNotFoundException("Product with id " + id + " not found.");
        }
    }

    public Long addProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct.getId();
    }

    public void deleteProduct(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("Product with id " + id + " not found.");
        }
    }

    public void updateProduct(long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product productInDb = optionalProduct.get();
            productInDb.setName(product.getName());
            productInDb.setKind(product.getKind());
            productInDb.setPackaging(product.getPackaging());
            productInDb.setDescription(product.getDescription());
            productInDb.setPhoto(product.getPhoto());
            productInDb.setPrice(product.getPrice());
            productInDb.setVatPercentage(product.getVatPercentage());
            productRepository.save(productInDb);
        }
        else {
            throw new RecordNotFoundException("Product with id " + id + " not found.");
        }


    }
}
