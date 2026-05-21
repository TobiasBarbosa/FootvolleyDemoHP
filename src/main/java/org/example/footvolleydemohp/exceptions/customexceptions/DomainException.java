package org.example.footvolleydemohp.exceptions.customexceptions;

public abstract class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }
}
