package org.example.footvolleydemohp.service;

import lombok.RequiredArgsConstructor;
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
    private final TrainingEventService trainingEventService;
    private final UserAccountService userAccountService;

    //***BUSINESS LOGIC & CRUD***--------------------------------------------------------------------------------------C
    public Booking createBooking(Long userId, Long eventId) {

        UserAccount user = userAccountService.getUserById(userId);
        Event event = trainingEventService.getTrainingEventById(eventId);

        // 1. Prevent duplicate booking
        boolean alreadyBooked =
                bookingRepository.existsByUserIdAndTrainingEventId(userId, eventId);

        if (alreadyBooked) {
            throw new IllegalArgumentException("User is already booked for this event");
        }

        // 2. Check max participants
        int currentParticipants =
                bookingRepository.countByTrainingEventId(eventId);

        if (currentParticipants >= event.getMaxParticipants()) {
            throw new IllegalArgumentException("Event is fully booked");
        }

        // 3. Create booking
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
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        bookingRepository.delete(booking);
    }
}
