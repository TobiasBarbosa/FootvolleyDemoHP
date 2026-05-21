package org.example.footvolleydemohp.exceptions.customexceptions.userexceptions;

import org.example.footvolleydemohp.exceptions.customexceptions.DomainException;

public class InvalidUserDataException extends DomainException {

    public InvalidUserDataException(String message) {
        super(message);
    }

}
