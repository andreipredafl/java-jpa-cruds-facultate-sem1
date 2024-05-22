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
@Table(name = "contracts")
public class Contract {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String contractName;
    private String series;
    private String number;
    private Integer periodMonths;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
