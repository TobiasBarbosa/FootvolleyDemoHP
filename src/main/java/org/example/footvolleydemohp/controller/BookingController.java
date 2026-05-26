package org.example.footvolleydemohp.controller;

import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.model.Booking;
import org.example.footvolleydemohp.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    //***ACCESS ATTRIBUTES***-------------------------------------------------------------------------------------------
    private final BookingService bookingService;

    //***CRUD***--------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam Long userId, @RequestParam Long eventId) {
        Booking bookingCreated = bookingService.createBooking(userId, eventId);
        return ResponseEntity
                .created(URI.create("/api/bookings/" + bookingCreated.getId()))
                .body(bookingCreated);
    }

    //-----------------------------------------------------------------------------------------------------------------R
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookingsById(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
    }

    //-----------------------------------------------------------------------------------------------------------------U
    //-----------------------------------------------------------------------------------------------------------------D
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

}
