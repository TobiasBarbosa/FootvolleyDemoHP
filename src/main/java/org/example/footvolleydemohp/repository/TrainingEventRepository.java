package org.example.footvolleydemohp.repository;

import org.example.footvolleydemohp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingEventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByTitle(String title);
}
