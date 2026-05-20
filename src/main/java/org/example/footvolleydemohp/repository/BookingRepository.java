package org.example.footvolleydemohp.repository;

import org.example.footvolleydemohp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findbyId (Long id);
}
