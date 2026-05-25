package org.example.footvolleydemohp.service;

import lombok.RequiredArgsConstructor;
import org.example.footvolleydemohp.exceptions.customexceptions.bookingexceptions.BookingAlreadyExistsException;
import org.example.footvolleydemohp.exceptions.customexceptions.bookingexceptions.BookingNotFoundException;
import org.example.footvolleydemohp.exceptions.customexceptions.eventexceptions.EventFullyBookedException;
import org.example.footvolleydemohp.model.Booking;
import org.example.footvolleydemohp.model.Event;
import org.example.footvolleydemohp.model.UserAccount;
import org.example.footvolleydemohp.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingService {

    //***ACCESS ATTRIBUTES***-------------------------------------------------------------------------------------------
    private final BookingRepository bookingRepository;
    private final EventService eventService;
    private final UserAccountService userAccountService;

    //***BUSINESS LOGIC & CRUD***--------------------------------------------------------------------------------------C
    public Booking createBooking(Long userId, Long eventId) {

        UserAccount user = userAccountService.getUserById(userId);
        Event event = eventService.getEventById(eventId);

        // Prevent duplicate booking
        if (bookingRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new BookingAlreadyExistsException(userId, eventId);
        }

        // Checks max participants
        int currentParticipants =
                bookingRepository.countByEventId(eventId);

        if (currentParticipants >= event.getMaxParticipants()) {
            throw new EventFullyBookedException(eventId);
        }

        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setEvent(event);
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    //-----------------------------------------------------------------------------------------------------------------R
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    //-----------------------------------------------------------------------------------------------------------------U
    //-----------------------------------------------------------------------------------------------------------------D
    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));

        bookingRepository.delete(booking);
    }
}
