package org.example.footvolleydemohp.controller;

import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.model.Event;
import org.example.footvolleydemohp.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    //***ACCESS ATTRIBUTES***-------------------------------------------------------------------------------------------
    private final EventService eventService;

    //***API MAPPING***-------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        Event eventCreated = eventService.createEvent(event);
        return ResponseEntity.status(201).body(eventCreated); //TODO status code?
    }

    //-----------------------------------------------------------------------------------------------------------------R
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    //-----------------------------------------------------------------------------------------------------------------U
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id,
                                                     @RequestBody Event event) {
        return ResponseEntity.ok(
                eventService.updateEvent(id, event)
        );
    }

    //-----------------------------------------------------------------------------------------------------------------D
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

}
