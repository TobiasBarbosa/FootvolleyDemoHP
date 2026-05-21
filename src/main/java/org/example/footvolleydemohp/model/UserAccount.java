package org.example.footvolleydemohp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA: gør klassen til en database-tabel (Hibernate mapper den ved startup)
@Getter
@Setter
@NoArgsConstructor // Java: kræves af JPA for at kunne instantiere objektet via reflection
@Table(name = "user_account") // JPA: definerer tabelnavn i databasen

public class UserAccount {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    @Id // JPA: markerer primary key (bruges i persistence layer)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA: database genererer ID (AUTO INCREMENT)
    private Long id;

    @NotBlank // Validation: aktiveres kun når @Valid bruges (typisk i controller)
    @Size(max = 100) // Validation: tjekker længde FØR data når database/service
    @Column(nullable = false, length = 100) // JPA: DB constraint (NOT NULL + max length)
    private String firstName;

    @NotBlank // Validation layer (Bean Validation / Hibernate Validator)
    @Size(max = 100) // Validation layer
    @Column(nullable = false, length = 100) // JPA: database constraint
    private String lastName;

    @NotBlank // Validation: felt må ikke være null eller tomt
    @Email // Validation: tjekker korrekt email-format (kun ved @Valid)
    @Size(max = 255) // Validation: begrænser længde før DB
    @Column(nullable = false, unique = true, length = 255) // JPA: DB constraint + unique index
    private String email;

    @Size(max = 255) // Validation: valgfrit felt længdebegrænsning
    @Column(length = 255) // JPA: kolonne længde i DB
    private String addressLine;

    @Size(max = 100) // Validation layer
    @Column(length = 100) // JPA layer
    private String city;

    @Size(max = 20) // Validation layer
    @Column(length = 20) // JPA layer
    private String postalCode;

    @Size(max = 100) // Validation layer
    @Column(length = 100) // JPA layer
    private String country;

    @NotBlank // Validation: sikrer password ikke er tomt (kun API-level check)
    @Column(nullable = false, length = 255) // JPA: DB kræver værdi
    private String password;

    @Column(nullable = false) // JPA: DB constraint (boolean må ikke være null)
    private boolean isMember;

    @NotNull // Validation: sikrer enum er sat før save (via @Valid)
    @Enumerated(EnumType.STRING) // JPA: gemmer enum som STRING i databasen
    @Column(nullable = false, length = 50) // JPA: DB constraint
    private Role role;
}
