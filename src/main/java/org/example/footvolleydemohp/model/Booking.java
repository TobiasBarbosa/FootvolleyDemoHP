package org.example.footvolleydemohp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "booking",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "training_event_id"})
        }
)
public class Booking {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    //hej
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "training_event_id", nullable = false)
    private Event event;
}
