package com.example.it_company.backend;

import com.example.it_company.backend.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository repository;

    @Autowired
    public ContractService(ContractRepository repository) {
        this.repository = repository;
    }

    public List<Contract> findAll() {
        return repository.findAll();
    }

    public Contract save(Contract contract) {
        return repository.save(contract);
    }

    public void delete(Contract contract) {
        repository.delete(contract);
    }
}
