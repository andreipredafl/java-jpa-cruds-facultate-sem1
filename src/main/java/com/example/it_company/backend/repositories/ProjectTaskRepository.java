package com.example.it_company.backend.repositories;

import com.example.it_company.backend.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
}
