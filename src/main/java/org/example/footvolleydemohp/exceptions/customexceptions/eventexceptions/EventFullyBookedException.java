package org.example.footvolleydemohp.exceptions.customexceptions.eventexceptions;

import org.example.footvolleydemohp.exceptions.customexceptions.DomainException;

public class EventFullyBookedException extends DomainException {

    public EventFullyBookedException(Long eventId) {
        super("Event is fully booked with id: " + eventId);
    }
}
