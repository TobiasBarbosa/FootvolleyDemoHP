package org.example.footvolleydemohp.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * RESPONSE DTO (OUTPUT LAYER)
 *
 * NOTE:
 * - Used for API responses (server → client)
 * - Created manually in Mapper layer
 * - Immutable design (no setters)
 * - Ensures stable API contract between backend and frontend
 *
 * FUTURE NOTE:
 * - Could be converted to a record once API stabilizes
 */
@Getter
@AllArgsConstructor
public class EventResponseDTO {

    private Long id;
    private String title;
    private String eventType;
    private String trainingLevel;
    private String locationName;
    private String locationAddress;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double price;
    private Integer maxParticipants;
    private String description;
}
