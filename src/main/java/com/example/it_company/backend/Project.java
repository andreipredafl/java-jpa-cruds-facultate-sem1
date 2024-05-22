package com.example.it_company.backend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "projects")
public class Project {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "project")
    private List<ProjectTeam> projectTeams;
}
