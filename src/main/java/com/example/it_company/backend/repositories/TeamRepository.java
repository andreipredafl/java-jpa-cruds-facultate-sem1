package com.example.it_company.backend.repositories;

import com.example.it_company.backend.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
