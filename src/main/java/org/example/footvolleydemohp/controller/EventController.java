package org.example.footvolleydemohp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.dto.event.CreateEventRequestDTO;
import org.example.footvolleydemohp.dto.event.EventResponseDTO;
import org.example.footvolleydemohp.mapper.EventMapper;
import org.example.footvolleydemohp.model.Event;
import org.example.footvolleydemohp.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    //***ACCESS ATTRIBUTES***-------------------------------------------------------------------------------------------
    private final EventService eventService;
    private final EventMapper eventMapper;

    //***CRUD***--------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
        @PostMapping
        public ResponseEntity<EventResponseDTO> createEvent(@Valid @RequestBody CreateEventRequestDTO dto) {
            Event event = eventMapper.toEntity(dto);
            Event saved = eventService.createEvent(event);

            return ResponseEntity.created(URI.create("/api/events/" + saved.getId()))
                                 .body(eventMapper.toResponseDTO(saved));
        }

    //-----------------------------------------------------------------------------------------------------------------R
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);

        return ResponseEntity.ok(eventMapper.toResponseDTO(event));
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> events = eventService.getAllEvents()
                .stream()
                .map(eventMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(events);
    }

    //-----------------------------------------------------------------------------------------------------------------U
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @Valid @RequestBody CreateEventRequestDTO dto) {
        Event updatedEvent = eventMapper.toEntity(dto);

        Event saved = eventService.updateEvent(id, updatedEvent);

        return ResponseEntity.ok(eventMapper.toResponseDTO(saved));
    }

    //-----------------------------------------------------------------------------------------------------------------D
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);

        return ResponseEntity.noContent().build();
    }
}