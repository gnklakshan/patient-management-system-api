package com.patientmanagement.patientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String address;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private LocalDate registrationDate;
}

