package org.example.footvolleydemohp.service;

import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.exceptions.customexceptions.eventexceptions.EventNotFoundException;
import org.example.footvolleydemohp.exceptions.customexceptions.eventexceptions.InvalidEventException;
import org.example.footvolleydemohp.model.Event;
import org.example.footvolleydemohp.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {

    //***DEPENDENCIES***------------------------------------------------------------------------------------------------
    private final EventRepository eventRepository;

    //***BUSINESS LOGIC & CRUD***---------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
    public Event createEvent(Event event) {
        validateEvent(event);
        return eventRepository.save(event);
    }

    //-----------------------------------------------------------------------------------------------------------------R
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                              .orElseThrow(() -> new EventNotFoundException(id));
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    //-----------------------------------------------------------------------------------------------------------------U
    public Event updateEvent(Long id, Event updatedEvent) {

        Event existingEvent = getEventById(id);

        validateEvent(updatedEvent);

        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setEventType(updatedEvent.getEventType());
        existingEvent.setTrainingLevel(updatedEvent.getTrainingLevel());
        existingEvent.setLocationName(updatedEvent.getLocationName());
        existingEvent.setLocationAddress(updatedEvent.getLocationAddress());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setStartTime(updatedEvent.getStartTime());
        existingEvent.setEndTime(updatedEvent.getEndTime());
        existingEvent.setPrice(updatedEvent.getPrice());
        existingEvent.setMaxParticipants(updatedEvent.getMaxParticipants());
        existingEvent.setDescription(updatedEvent.getDescription());

        return eventRepository.save(existingEvent);
    }

    //-----------------------------------------------------------------------------------------------------------------D
    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }

    //***VALIDATION METHODS***------------------------------------------------------------------------------------------
    private void validateEvent(Event event) {

        // End time must be after start time
        if (event.getStartTime() != null &&
            event.getEndTime() != null &&
            event.getEndTime().isBefore(event.getStartTime())) {
            throw new InvalidEventException("End time cannot be before start time");
        }

        // Date cannot be in the past
        if (event.getDate() != null &&
            event.getDate().isBefore(LocalDate.now())) {
            throw new InvalidEventException("Event date cannot be in the past");
        }

        // Price cannot be negative
        if (event.getPrice() != null &&
            event.getPrice() < 0) {
            throw new InvalidEventException("Price cannot be negative");
        }

        // Max participants must be above 0
        if (event.getMaxParticipants() != null && event.getMaxParticipants() <= 0) {

            throw new InvalidEventException("Max participants must be greater than 0");
        }
    }
}
