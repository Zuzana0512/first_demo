package nl.novi.first_demo.controller;

import nl.novi.first_demo.model.Product;
import nl.novi.first_demo.model.Supplier;
import nl.novi.first_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


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
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable long id) {
        return productService.getProduct(id);
    }

    @PostMapping(value = "/products")
    public ResponseEntity addProduct(@RequestBody Product product){
        Long newId = productService.addProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body("Added " + newId);
    }

    @DeleteMapping(value = "/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return "Product removed";
    }

    @PutMapping(value = "/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateProduct(@PathVariable long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return "Product updated";
    }

}
