package org.example.footvolleydemohp.dto.event;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CreateEventRequestDTO {

    /**
     * NOTE:
     * This is a REQUEST DTO (input layer).
     *
     * - Used for incoming JSON from frontend
     * - Mutable to support Spring/Jackson data binding
     * - Designed for flexibility during active development
     * - May be refactored later when API stabilizes
     *
     * RULE:
     * Spring instantiates this using no-args constructor + setters
     */

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotNull
    private String eventType; // String used for flexibility during development (can later become enum)

    private String trainingLevel;

    @NotBlank
    private String locationName;

    @NotBlank
    private String locationAddress;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    @PositiveOrZero
    private Double price;

    @NotNull
    @Positive
    private Integer maxParticipants;

    @Size(max = 500)
    private String description;
}