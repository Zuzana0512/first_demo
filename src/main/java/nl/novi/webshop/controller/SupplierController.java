package nl.novi.webshop.controller;

import nl.novi.webshop.model.Supplier;
import nl.novi.webshop.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping(value = "/suppliers")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Supplier> getSuppliers() {
        return supplierService.getSuppliers();
    }

    @GetMapping(value = "/suppliers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier getSupplier(@PathVariable long id) {
        return supplierService.getSupplier(id);
    }

    @PostMapping(value = "/suppliers")
    public ResponseEntity addSupplier(@RequestBody Supplier supplier){
        Long newId = supplierService.addSupplier(supplier);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body("Added " + newId);
    }

    @DeleteMapping(value = "/suppliers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteSupplier(@PathVariable long id){
        supplierService.deleteSupplier(id);
        return "Deleted";
    }

    @PutMapping(value = "/suppliers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateSupplier(@PathVariable long id, @RequestBody Supplier supplier){
        supplierService.updateSupplier(id, supplier);
        return "Updated";
    }

}
