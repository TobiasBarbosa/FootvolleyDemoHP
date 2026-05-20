package org.example.footvolleydemohp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "booking",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "training_event_id"})
        }
)
public class Booking {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "training_event_id", nullable = false)
    private Event event;
}
