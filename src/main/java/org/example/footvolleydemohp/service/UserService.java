package org.example.footvolleydemohp.service;

import org.example.footvolleydemohp.model.Booking;
import org.example.footvolleydemohp.model.TrainingEvent;
import org.example.footvolleydemohp.model.UserAccount;
import org.example.footvolleydemohp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //ACCESS ATTRIBUTES***----------------------------------------------------------------------------------------------
    @Autowired
    private BookingRepository bookingRepository;

    //***SERVICE LOGIC***-----------------------------------------------------------------------------------------------
    public Booking createBooking(UserAccount user, TrainingEvent trainingEvent){
        Booking booking = new Booking();
        booking.

    }

}
