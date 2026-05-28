package org.example.footvolleydemohp.repository;

import org.example.footvolleydemohp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
   boolean existsByUserIdAndEventId(Long userId, Long trainingEventId);

    int countByEventId(Long trainingEventId);

    List<Booking> findByUserId(Long userId);
}
