package org.example.footvolleydemohp.exceptions.customexceptions.userexceptions;

import org.example.footvolleydemohp.exceptions.customexceptions.DomainException;

public class UserAlreadyExistsException extends DomainException {

    public UserAlreadyExistsException(String email) {
        super("User already exists with email: " + email);
    }
}
