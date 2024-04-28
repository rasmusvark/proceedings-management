package com.example.proceedingsmanagement.domain.proceeding;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "proceedings")
public class Proceeding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "personalId", nullable = false)
    private String personalId;

    @NotNull
    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "emailSent")
    private boolean emailSent;
}