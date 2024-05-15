package com.example.agc.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SupplierService implements CrudListener<Supplier> {

    private final SupplierRepository repository;

    @Override
    public Collection<Supplier> findAll() {
        return repository.findAll();
    }

    @Override
    public Supplier add(Supplier employee) {
        return repository.save(employee);
    }

    @Override
    public Supplier update(Supplier employee) {
        return repository.save(employee);
    }

    @Override
    public void delete(Supplier employee) {
        repository.delete(employee);
    }
}
