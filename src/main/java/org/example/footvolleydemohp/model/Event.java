package org.example.footvolleydemohp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.footvolleydemohp.model.enums.EventType;
import org.example.footvolleydemohp.model.enums.TrainingLevel;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private TrainingLevel trainingLevel;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String locationName;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String locationAddress;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Column(nullable = false)
    private LocalTime startTime;

    @NotNull
    @Column(nullable = false)
    private LocalTime endTime;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Double price;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer maxParticipants;

    @Size(max = 500)
    @Column(length = 500)
    private String description;
}
