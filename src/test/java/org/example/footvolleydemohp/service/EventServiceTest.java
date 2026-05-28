package org.example.footvolleydemohp.service;

import org.example.footvolleydemohp.exceptions.customexceptions.eventexceptions.EventNotFoundException;
import org.example.footvolleydemohp.exceptions.customexceptions.eventexceptions.InvalidEventException;
import org.example.footvolleydemohp.model.Event;
import org.example.footvolleydemohp.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    //***DEPENDENCIES (FAKE)***-----------------------------------------------------------------------------------------
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    //***CRUD UNIT-TEST***----------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------C
    @Test
    void createEvent_shouldSaveEvent_whenValid() {
        //ARRANGE
        Event event = new Event();
        event.setTitle("Training");
        event.setStartTime(LocalTime.of(10, 0));
        event.setEndTime(LocalTime.of(12, 0));
        event.setDate(LocalDate.now().plusDays(1));
        event.setPrice(50.0);
        event.setMaxParticipants(10);

        //ACT
        when(eventRepository.save(event)).thenReturn(event);
        Event result = eventService.createEvent(event);
        //ASSERT
        assertNotNull(result);
        assertEquals("Training", result.getTitle());

        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void createEvent_shouldThrowException_whenEndTimeBeforeStartTime() {
        //ARRANGE
        Event event = new Event();
        event.setStartTime(LocalTime.of(14, 0));
        event.setEndTime(LocalTime.of(12, 0));
        event.setDate(LocalDate.now().plusDays(1));
        event.setPrice(50.0);
        event.setMaxParticipants(10);

        //ACT
        assertThrows(InvalidEventException.class, () -> eventService.createEvent(event));

        //ASSERT
        verify(eventRepository, never()).save(any());
    }

    @Test
    void createEvent_shouldThrowException_whenDateInPast() {
        //ARRANGE
        Event event = new Event();
        event.setStartTime(LocalTime.of(10, 0));
        event.setEndTime(LocalTime.of(12, 0));
        event.setDate(LocalDate.now().minusDays(1));
        event.setPrice(50.0);
        event.setMaxParticipants(10);

        //ASSERT
        assertThrows(InvalidEventException.class, () -> eventService.createEvent(event));
    }

    //-----------------------------------------------------------------------------------------------------------------R
    @Test
    void getEventById_shouldReturnEvent_whenExists() {
        //ARRANGE
        Event event = new Event();
        event.setId(1L);

        //ACT
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        Event result = eventService.getEventById(1L);

        //ASSERT
        assertEquals(1L, result.getId());
    }

    @Test
    void getEventById_shouldThrowException_whenNotFound() {

        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EventNotFoundException.class,
                () -> eventService.getEventById(1L));
    }

    //-----------------------------------------------------------------------------------------------------------------U

}
