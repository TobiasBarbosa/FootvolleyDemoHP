package org.example.footvolleydemohp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //*** CENTRAL ERROR RESPONSE STRUCTURE ***-------------------------------
    // This defines the JSON structure returned to the client
    // Instead of returning raw exceptions, we return a clean format
    public record ErrorResponse(
            String message,     // Human-readable error message
            int status,         // HTTP status code
            LocalDateTime timestamp // When the error happened
    ) {}

    //*** 400 - BAD REQUEST (client input errors) ---------------------------
    // Triggered when the client sends invalid data or violates simple rules
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        // 400 means: client error (wrong input)
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //*** 404 - NOT FOUND (missing resources) ------------------------------
    // Used when requested data does not exist in database
    @ExceptionHandler({
            org.example.footvolleydemohp.exceptions.customexceptions.userexceptions.UserNotFoundException.class,
            org.example.footvolleydemohp.exceptions.customexceptions.bookingexceptions.BookingNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        // 404 means: resource does not exist
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //*** 409 - CONFLICT (business rule violations) ------------------------
    // Used when request is valid but conflicts with business rules
    // Example: duplicate booking, fully booked event
    @ExceptionHandler({
            org.example.footvolleydemohp.exceptions.customexceptions.bookingexceptions.BookingAlreadyExistsException.class,
            org.example.footvolleydemohp.exceptions.customexceptions.eventexceptions.EventFullyBookedException.class
    })
    public ResponseEntity<ErrorResponse> handleConflict(RuntimeException ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );

        // 409 means: request conflicts with system state
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    //*** 500 - INTERNAL SERVER ERROR (unexpected failures) ----------------
    // Fallback for everything not explicitly handled above
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

        ErrorResponse error = new ErrorResponse(
                "Unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );

        // 500 means: server-side unexpected failure
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
