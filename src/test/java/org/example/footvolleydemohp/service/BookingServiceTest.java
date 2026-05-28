package org.example.footvolleydemohp.service;

import org.example.footvolleydemohp.exceptions.customexceptions.bookingexceptions.BookingAlreadyExistsException;
import org.example.footvolleydemohp.exceptions.customexceptions.bookingexceptions.BookingNotFoundException;
import org.example.footvolleydemohp.exceptions.customexceptions.eventexceptions.EventFullyBookedException;
import org.example.footvolleydemohp.model.Booking;
import org.example.footvolleydemohp.model.Event;
import org.example.footvolleydemohp.model.UserAccount;
import org.example.footvolleydemohp.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    //***DEPENDENCIES***------------------------------------------------------------------------------------------------
    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private EventService eventService;

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private BookingService bookingService;

    //***CRUD TEST***---------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
    @Test
    void createBooking_shouldCreateBooking_whenValid() {

        Long userId = 1L;
        Long eventId = 1L;

        UserAccount user = new UserAccount();
        Event event = new Event();
        event.setMaxParticipants(10);

        when(userAccountService.getUserById(userId)).thenReturn(user);
        when(eventService.getEventById(eventId)).thenReturn(event);
        when(bookingRepository.existsByUserIdAndEventId(userId, eventId)).thenReturn(false);
        when(bookingRepository.countByEventId(eventId)).thenReturn(0);

        Booking booking = new Booking();
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking result = bookingService.createBooking(userId, eventId);

        assertNotNull(result);

        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void createBooking_shouldThrowException_whenDuplicateBooking() {

        Long userId = 1L;
        Long eventId = 1L;

        when(bookingRepository.existsByUserIdAndEventId(userId, eventId))
                .thenReturn(true);

        assertThrows(BookingAlreadyExistsException.class,
                () -> bookingService.createBooking(userId, eventId));

        verify(bookingRepository, never()).save(any());
    }

    @Test
    void createBooking_shouldThrowException_whenEventFullyBooked() {

        Long userId = 1L;
        Long eventId = 1L;

        UserAccount user = new UserAccount();
        Event event = new Event();
        event.setMaxParticipants(1);

        when(userAccountService.getUserById(userId)).thenReturn(user);
        when(eventService.getEventById(eventId)).thenReturn(event);

        when(bookingRepository.existsByUserIdAndEventId(userId, eventId)).thenReturn(false);
        when(bookingRepository.countByEventId(eventId)).thenReturn(1);

        assertThrows(EventFullyBookedException.class, () -> bookingService.createBooking(userId, eventId));

        verify(bookingRepository, never()).save(any());
    }

    //***--------------------------------------------------------------------------------------------------------------D
    @Test
    void deleteBooking_shouldDelete_whenExists() {

        Booking booking = new Booking();

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        bookingService.deleteBooking(1L);

        verify(bookingRepository, times(1)).delete(booking);
    }

    @Test
    void deleteBooking_shouldThrowException_whenNotFound() {

        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, () -> bookingService.deleteBooking(1L));
    }
}
