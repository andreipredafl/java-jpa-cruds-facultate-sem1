package com.example.it_company.backend.services;

import com.example.it_company.backend.ProjectTask;
import com.example.it_company.backend.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    private final ProjectTaskRepository repository;

    @Autowired
    public ProjectTaskService(ProjectTaskRepository repository) {
        this.repository = repository;
    }

    public List<ProjectTask> findAll() {
        return repository.findAll();
    }

    public ProjectTask save(ProjectTask task) {
        return repository.save(task);
    }

    public void delete(ProjectTask task) {
        repository.delete(task);
    }
}
