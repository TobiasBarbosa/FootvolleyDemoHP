package org.example.footvolleydemohp.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * RESPONSE DTO (OUTPUT LAYER)
 *
 * NOTE:
 * - Represents booking data returned to client
 * - Built in Mapper layer (Entity → DTO)
 * - Immutable design ensures safe API contract
 * - Prevents accidental modification of response data
 *
 * FUTURE NOTE:
 * - Candidate for record when API stabilizes
 */
@Getter
@AllArgsConstructor
public class BookingResponseDTO {

    private Long id;
    private Long userId;
    private Long eventId;
    private LocalDateTime bookingTime;
}
