package nl.novi.first_demo.controller;

import nl.novi.first_demo.model.Supplier;
import nl.novi.first_demo.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController

public class SupplierController {
    @Autowired
    private static SupplierService supplierService;

    @GetMapping(value = "/suppliers")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Supplier> getSupplier() {
        return supplierService.getSuppliers();
    }

    @GetMapping(value = "/suppliers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier getSupplier(@PathVariable int id) {
        return supplierService.getSupplier(id);
    }

    @GetMapping(value = "/suppliers")
    @ResponseStatus(HttpStatus.CREATED)
    public String addSupplier(@RequestBody Supplier supplier){
        supplierService.addSupplier(supplier);
        return "Added";
    }

    @GetMapping(value = "/suppliers/{id}")
    public String deleteSupplier(@PathVariable int id){
        supplierService.deleteSupplier(id);
        return "Deleted";
    }

    @GetMapping(value = "/suppliers/{id}")
    public String updateSupplier(@PathVariable int id, @RequestBody Supplier supplier){
        supplierService.updateSupplier(id, supplier);
        return "Updated";
    }

}
