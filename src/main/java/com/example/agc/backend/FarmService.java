package com.example.agc.backend;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService implements CrudListener<Farm> {

    private final FarmRepository repository;

    @Override
    public Collection<Farm> findAll() {
        return repository.findAll();
    }

    @Override
    public Farm add(Farm farm) {
        return repository.save(farm);
    }

    @Override
    public Farm update(Farm farm) {
        return repository.save(farm);
    }

    @Override
    public void delete(Farm farm) {
        repository.delete(farm);
    }

}
