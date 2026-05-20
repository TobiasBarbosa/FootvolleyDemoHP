package org.example.footvolleydemohp.controller;

import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.model.Event;
import org.example.footvolleydemohp.service.TrainingEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    //***ACCESS ATTRIBUTES***-------------------------------------------------------------------------------------------
    private final TrainingEventService trainingEventService;

    //***API MAPPING***-------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        Event eventCreated = trainingEventService.createTrainingEvent(event);
        return ResponseEntity.status(201).body(eventCreated); //TODO status code?
    }

    //-----------------------------------------------------------------------------------------------------------------R
    @GetMapping
    public ResponseEntity<List<Event>> getAllTrainingEvents() {
        return ResponseEntity.ok(trainingEventService.getAllTrainingEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getTrainingEventById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingEventService.getTrainingEventById(id));
    }

    //-----------------------------------------------------------------------------------------------------------------U
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateTrainingEvent(@PathVariable Long id,
                                                     @RequestBody Event event) {
        return ResponseEntity.ok(
                trainingEventService.updateTrainingEvent(id, event)
        );
    }

    //-----------------------------------------------------------------------------------------------------------------D
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingEvent(@PathVariable Long id) {
        trainingEventService.deleteTrainingEvent(id);
        return ResponseEntity.noContent().build();
    }

}
