package org.example.footvolleydemohp.repository;

import org.example.footvolleydemohp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findbyId (Long id);

    boolean existsByUserIdAndTrainingEventId(Long userId, Long trainingEventId);

    int countByTrainingEventId(Long trainingEventId);

    List<Booking> findByUserId(Long userId);
}
