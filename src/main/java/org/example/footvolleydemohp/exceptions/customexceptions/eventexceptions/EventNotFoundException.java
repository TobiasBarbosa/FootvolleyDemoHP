package org.example.footvolleydemohp.exceptions.customexceptions.eventexceptions;

import org.example.footvolleydemohp.exceptions.customexceptions.DomainException;

public class EventNotFoundException extends DomainException {

    public EventNotFoundException(Long id) {
        super("Event not found with id: " + id);
    }
}
