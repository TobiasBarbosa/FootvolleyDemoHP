package org.example.footvolleydemohp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "training_event")
public class TrainingEvent {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private TrainingLevel trainingLevel;

    @Column(nullable = false, length = 150)
    private String locationName;

    @Column(nullable = false, length = 255)
    private String locationAddress;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer maxParticipants;

    @Column(length = 500)
    private String description;
}
