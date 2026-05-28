package org.example.footvolleydemohp.mapper;

import org.example.footvolleydemohp.dto.booking.BookingResponseDTO;
import org.example.footvolleydemohp.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponseDTO toResponseDTO(Booking booking) {
        return new BookingResponseDTO(
                booking.getId(),
                booking.getUser().getId(),
                booking.getEvent().getId(),
                booking.getBookingTime()
        );
    }
}