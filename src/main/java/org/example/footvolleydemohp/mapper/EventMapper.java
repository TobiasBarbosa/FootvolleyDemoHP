package org.example.footvolleydemohp.mapper;

import org.example.footvolleydemohp.dto.event.CreateEventRequestDTO;
import org.example.footvolleydemohp.dto.event.EventResponseDTO;
import org.example.footvolleydemohp.model.Event;
import org.example.footvolleydemohp.model.enums.EventType;
import org.example.footvolleydemohp.model.enums.TrainingLevel;
import org.springframework.stereotype.Component;

/**
 * EVENT MAPPER
 *
 * NOTE:
 * - Responsible ONLY for converting between Entity and DTOs
 * - Must NOT contain business logic
 * - Keeps Controller/Service clean
 */
@Component
public class EventMapper {

    // ----------------------------
    // REQUEST DTO → ENTITY
    // ----------------------------
    public Event toEntity(CreateEventRequestDTO dto) {

        Event event = new Event();

        event.setTitle(dto.getTitle());
        event.setEventType(EventType.valueOf(dto.getEventType()));

        if (dto.getTrainingLevel() != null) {
            event.setTrainingLevel(TrainingLevel.valueOf(dto.getTrainingLevel())
            );
        }

        event.setLocationName(dto.getLocationName());
        event.setLocationAddress(dto.getLocationAddress());
        event.setDate(dto.getDate());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());
        event.setPrice(dto.getPrice());
        event.setMaxParticipants(dto.getMaxParticipants());
        event.setDescription(dto.getDescription());

        return event;
    }

    // ----------------------------
    // ENTITY → RESPONSE DTO
    // ----------------------------
    public EventResponseDTO toResponseDTO(Event event) {

        return new EventResponseDTO(
                                    event.getId(),
                                    event.getTitle(),
                                    event.getEventType().name(),
                                    event.getTrainingLevel() != null ? event.getTrainingLevel().name() : null,
                                    event.getLocationName(),
                                    event.getLocationAddress(),
                                    event.getDate(),
                                    event.getStartTime(),
                                    event.getEndTime(),
                                    event.getPrice(),
                                    event.getMaxParticipants(),
                                    event.getDescription()
        );
    }
}