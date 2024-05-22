package com.example.it_company.backend.repositories;

import com.example.it_company.backend.Team;
import com.example.it_company.backend.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    List<TeamMember> findByTeam(Team team);
}
