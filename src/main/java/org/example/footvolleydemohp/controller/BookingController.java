package org.example.footvolleydemohp.controller;

import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.dto.booking.BookingResponseDTO;
import org.example.footvolleydemohp.dto.booking.CreateBookingRequestDTO;
import org.example.footvolleydemohp.mapper.BookingMapper;
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
    private final BookingMapper bookingMapper;

    //***CRUD***--------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
      @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody CreateBookingRequestDTO dto) {

        Booking booking = bookingService.createBooking(
                dto.getUserId(),
                dto.getEventId()
        );

        return ResponseEntity
                .created(URI.create("/api/bookings/" + booking.getId()))
                .body(bookingMapper.toResponseDTO(booking));
    }

    //-----------------------------------------------------------------------------------------------------------------R
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUser(@PathVariable Long userId) {

        List<BookingResponseDTO> bookings = bookingService.getBookingsByUser(userId)
                .stream()
                .map(bookingMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(bookings);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        List<BookingResponseDTO> bookings = bookingService.getAllBookings()
                .stream()
                .map(bookingMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(bookings);
    }

    //-----------------------------------------------------------------------------------------------------------------U
    //-----------------------------------------------------------------------------------------------------------------D
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);

        return ResponseEntity.noContent().build();
    }

}
