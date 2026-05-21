package org.example.footvolleydemohp.exceptions.customexceptions.eventexceptions;

import org.example.footvolleydemohp.exceptions.customexceptions.DomainException;

public class InvalidEventException extends DomainException {

    public InvalidEventException(String message) {
        super(message);
    }
}
