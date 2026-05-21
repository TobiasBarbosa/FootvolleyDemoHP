package org.example.footvolleydemohp.exceptions.customexceptions.userexceptions;

import org.example.footvolleydemohp.exceptions.customexceptions.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
}
