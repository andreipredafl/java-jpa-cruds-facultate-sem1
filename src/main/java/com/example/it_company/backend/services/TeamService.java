package com.example.it_company.backend.services;

import com.example.it_company.backend.Team;
import com.example.it_company.backend.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository repository;

    @Autowired
    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> findAll() {
        return repository.findAll();
    }

    public Team save(Team team) {
        return repository.save(team);
    }

    public void delete(Team team) {
        repository.delete(team);
    }
}
