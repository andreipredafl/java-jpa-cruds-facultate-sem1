package com.example.it_company.backend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "project_teams")
public class ProjectTeam {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private LocalDate assignedAt;
    private LocalDate unassignedAt;
}
