package org.example.footvolleydemohp.repository;

import org.example.footvolleydemohp.model.TrainingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingEventRepository extends JpaRepository<TrainingEvent, Long> {
    Optional<TrainingEvent> findByTitle(String title);
}
