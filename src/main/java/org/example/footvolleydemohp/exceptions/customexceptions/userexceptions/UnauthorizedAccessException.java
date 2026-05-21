package org.example.footvolleydemohp.exceptions.customexceptions.userexceptions;

import org.example.footvolleydemohp.exceptions.customexceptions.DomainException;
import org.example.footvolleydemohp.model.Role;

public class UnauthorizedAccessException extends DomainException {

    public UnauthorizedAccessException(Role role) {
        super("Role: '" + role + "' is not authorized to perform this action");
    }
}
