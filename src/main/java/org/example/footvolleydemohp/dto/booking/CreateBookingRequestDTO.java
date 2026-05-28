package org.example.footvolleydemohp.dto.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * REQUEST DTO (INPUT LAYER)
 *
 * NOTE:
 * - Represents booking creation request from frontend
 * - Mutable for Spring data binding (JSON → object)
 * - Simple structure: only IDs needed (user + event)
 * - Can evolve during development phase without breaking API
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateBookingRequestDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long eventId;
}
