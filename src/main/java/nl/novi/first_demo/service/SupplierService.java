package nl.novi.first_demo.service;

import nl.novi.first_demo.exeption.RecordNotFoundException;
import nl.novi.first_demo.model.Supplier;
import nl.novi.first_demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Iterable<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            return supplier.get();
        } else {
            throw new RecordNotFoundException("Supplier with id " + id + " not found.");
        }
    }

    public Long addSupplier(Supplier supplier) {
        Supplier newSupplier = supplierRepository.save(supplier);
        return newSupplier.getId();
    }

    public void deleteSupplier(long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
        }
        else{
            throw new RecordNotFoundException("Supplier with id " + id + " not found.");
        }
    }

    public void updateSupplier(long id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if(optionalSupplier.isPresent()){
            Supplier supplierInDb = optionalSupplier.get();
            supplierInDb.setName(supplier.getName());
            supplierInDb.setAddress(supplier.getAddress());
            supplierInDb.setProduct(supplier.getProduct());
            supplierRepository.save(supplierInDb);
        }
        else{
            throw new RecordNotFoundException("Supplier with id " + id + " not found.");
        }
    }


}

