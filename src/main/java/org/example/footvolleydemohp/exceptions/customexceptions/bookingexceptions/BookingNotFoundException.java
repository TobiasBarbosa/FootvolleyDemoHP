package org.example.footvolleydemohp.exceptions.customexceptions.bookingexceptions;

import org.example.footvolleydemohp.exceptions.customexceptions.DomainException;

public class BookingNotFoundException extends DomainException {

    public BookingNotFoundException(Long id) {
        super("Booking not found with id: " + id);
    }
}
