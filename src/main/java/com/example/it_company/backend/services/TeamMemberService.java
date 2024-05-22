package com.example.it_company.backend.services;

import com.example.it_company.backend.Team;
import com.example.it_company.backend.TeamMember;
import com.example.it_company.backend.repositories.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService {

    private final TeamMemberRepository repository;

    @Autowired
    public TeamMemberService(TeamMemberRepository repository) {
        this.repository = repository;
    }

    public List<TeamMember> findAll() {
        return repository.findAll();
    }

    public List<TeamMember> findByTeam(Team team) {
        return repository.findByTeam(team);
    }

    public TeamMember save(TeamMember teamMember) {
        return repository.save(teamMember);
    }

    public void delete(TeamMember teamMember) {
        repository.delete(teamMember);
    }
}
