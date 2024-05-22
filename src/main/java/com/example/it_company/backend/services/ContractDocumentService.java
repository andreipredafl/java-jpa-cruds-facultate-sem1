package com.example.it_company.backend.services;

import com.example.it_company.backend.ContractDocument;
import com.example.it_company.backend.repositories.ContractDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractDocumentService {

    private final ContractDocumentRepository repository;

    @Autowired
    public ContractDocumentService(ContractDocumentRepository repository) {
        this.repository = repository;
    }

    public List<ContractDocument> findAll() {
        return repository.findAll();
    }

    public ContractDocument save(ContractDocument document) {
        return repository.save(document);
    }

    public void delete(ContractDocument document) {
        repository.delete(document);
    }
}
