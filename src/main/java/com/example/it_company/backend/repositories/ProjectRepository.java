package com.example.it_company.backend.repositories;

import com.example.it_company.backend.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
