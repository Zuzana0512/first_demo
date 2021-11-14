package nl.novi.webshop.service;

import nl.novi.webshop.model.Supplier;

public interface SupplierService {
    public Iterable<Supplier> getSuppliers();

    public Supplier getSupplier(long id);

    public Long addSupplier(Supplier supplier);

    public void deleteSupplier(long id);

    public void updateSupplier(long id, Supplier supplier);
}
