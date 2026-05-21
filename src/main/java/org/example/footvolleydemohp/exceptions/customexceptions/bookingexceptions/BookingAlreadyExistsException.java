package org.example.footvolleydemohp.exceptions.customexceptions.bookingexceptions;

import org.example.footvolleydemohp.exceptions.customexceptions.DomainException;

public class BookingAlreadyExistsException extends DomainException {

    public BookingAlreadyExistsException(Long userId, Long eventId) {
        super("User with id " + userId + " is already booked for event with id " + eventId);
    }

}
