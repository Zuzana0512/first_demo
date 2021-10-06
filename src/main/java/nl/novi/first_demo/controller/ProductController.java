package nl.novi.first_demo.controller;

import nl.novi.first_demo.model.Product;
import nl.novi.first_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController

public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(value = "/products/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @PostMapping(value = "/products")
    @ResponseStatus(HttpStatus.OK)
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "Product added";
    }

    @DeleteMapping(value = "/products/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "Product removed";
    }

    @PutMapping(value = "/products/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return "Product updated";
    }

}
