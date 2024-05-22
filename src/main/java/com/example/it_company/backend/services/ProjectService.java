package com.example.it_company.backend.services;

import com.example.it_company.backend.Project;
import com.example.it_company.backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    @Autowired
    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> findAll() {
        return repository.findAll();
    }

    public Project save(Project project) {
        return repository.save(project);
    }

    public void delete(Project project) {
        repository.delete(project);
    }
}
